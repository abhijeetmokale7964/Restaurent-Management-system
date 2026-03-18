package com.restaurant.service;

import com.restaurant.entity.Bill;
import com.restaurant.entity.Payment;
import com.restaurant.entity.PaymentMethod;

public interface BillingService {

    Bill generateBill(Long orderId, Double discount);

    Bill getBillByOrderId(Long orderId);

    Payment makePayment(Long billId, PaymentMethod method);
}