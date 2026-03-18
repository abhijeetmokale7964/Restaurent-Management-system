package com.restaurant.controller;

import com.restaurant.entity.*;
import com.restaurant.service.BillingService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

    // Generate bill
    @PostMapping("/{orderId}")
    public ResponseEntity<Bill> generateBill(
            @PathVariable Long orderId,
            @RequestParam(required = false) Double discount) {

        logger.info("Generating bill");

        Bill bill = billingService.generateBill(orderId, discount);

        return ResponseEntity.of(Optional.ofNullable(bill));
    }

    // Get bill
    @GetMapping("/{orderId}")
    public ResponseEntity<Bill> getBill(@PathVariable Long orderId) {

        logger.info("Fetching bill");

        Bill bill = billingService.getBillByOrderId(orderId);

        return ResponseEntity.of(Optional.ofNullable(bill));
    }

    // Payment
    @PostMapping("/pay/{billId}")
    public ResponseEntity<Payment> payBill(
            @PathVariable Long billId,
            @RequestParam PaymentMethod method) {

        logger.info("Processing payment");

        Payment payment = billingService.makePayment(billId, method);

        return ResponseEntity.of(Optional.ofNullable(payment));
    }
}