package com.restaurant.entity;

import jakarta.persistence.*;
//import jakarta.persistence.criteria.Order;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;

import com.restaurant.enums.Role;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "email")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full name of user
//    @NotBlank(message = "Name is required")
//    @Column(nullable = false)
    private String name;

    // Email must be unique
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email is required")
//    @Column(nullable = false, unique = true)
    private String email;

    // Encrypted password
//    @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters")
//    @Column(nullable = false)
    private String password;

    // Role (ADMIN / STAFF)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Account status
    @Column(nullable = false)
    private Boolean active = true;

    // Created time
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Updated time
    private LocalDateTime updatedAt;

////    // One staff can handle many orders
//    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
//    private List<Order> orders;

    // Automatically set timestamps
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}