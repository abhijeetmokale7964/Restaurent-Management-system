package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Order reference
    private Long orderId;

    // Total before tax
    private Double totalAmount;

    // GST tax
    private Double tax;

    // Discount
    private Double discount;

    // Final amount
    private Double finalAmount;

    // Payment status
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}