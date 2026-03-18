package com.restaurant.controller;

import com.restaurant.entity.Customer;
import com.restaurant.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    // Create customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        logger.info("Creating new customer");

        Customer savedCustomer = customerService.saveCustomer(customer);

        return ResponseEntity.of(Optional.ofNullable(savedCustomer));
    }

    // Get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        logger.info("Fetching all customers");

        List<Customer> customers = customerService.getAllCustomers();

        return ResponseEntity.of(Optional.ofNullable(customers));
    }

    // Get customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        logger.info("Fetching customer by id: {}", id);

        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.of(Optional.ofNullable(customer));
    }

    // Update customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        logger.info("Updating customer");

        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        return ResponseEntity.of(Optional.ofNullable(updatedCustomer));
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        logger.info("Deleting customer");

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}