package com.restaurant.serviceImpl;

import com.restaurant.entity.*;
import com.restaurant.repository.*;
import com.restaurant.service.BillingService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    private static final Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);

    // Generate bill
    @Override
    public Bill generateBill(Long orderId, Double discount) {

        logger.info("Generating bill for orderId: {}", orderId);

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        if (items.isEmpty()) {
            logger.warn("No items found for orderId: {}", orderId);
            return null;
        }

        // Calculate total amount
        double totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // GST (5%)
        double tax = totalAmount * 0.05;

        // Discount
        double discountAmount = (discount != null) ? discount : 0.0;

        // Final amount
        double finalAmount = totalAmount + tax - discountAmount;

        Bill bill = new Bill();
        bill.setOrderId(orderId);
        bill.setTotalAmount(totalAmount);
        bill.setTax(tax);
        bill.setDiscount(discountAmount);
        bill.setFinalAmount(finalAmount);
        bill.setPaymentStatus(PaymentStatus.UNPAID);

        return billRepository.save(bill);
    }

    // Get bill
    @Override
    public Bill getBillByOrderId(Long orderId) {

        logger.info("Fetching bill for orderId: {}", orderId);

        return billRepository.findByOrderId(orderId);
    }

    // Make payment
    @Override
    public Payment makePayment(Long billId, PaymentMethod method) {

        logger.info("Processing payment for billId: {}", billId);

        Bill bill = billRepository.findById(billId).orElse(null);

        if (bill == null) {
            logger.error("Bill not found with id: {}", billId);
            return null;
        }

        // Create payment
        Payment payment = new Payment();
        payment.setBillId(billId);
        payment.setAmount(bill.getFinalAmount());
        payment.setPaymentMethod(method);
        payment.setPaymentDate(LocalDateTime.now());

        // Update bill
        bill.setPaymentStatus(PaymentStatus.PAID);
        billRepository.save(bill);

        return paymentRepository.save(payment);
    }
}