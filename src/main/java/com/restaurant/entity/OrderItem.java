package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Order reference
    private Long orderId;

    // Menu item reference
    private Long menuItemId;

    // Quantity of item
    private Integer quantity;

    // Price per item
    private Double price;
}