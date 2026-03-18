package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer ID
    private Long customerId;

    // Table ID
    private Long tableId;

    // Order time
    private LocalDateTime orderTime;

    // Order status
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}