package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer name
    private String name;

    // Phone number
    private String phone;

    // Email address
    private String email;

    // Customer address
    private String address;
}