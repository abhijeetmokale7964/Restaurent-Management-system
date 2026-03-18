package com.restaurant.controller;

import com.restaurant.entity.Order;
import com.restaurant.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    // Create order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        logger.info("Creating order");

        Order savedOrder = orderService.createOrder(order);

        return ResponseEntity.of(Optional.ofNullable(savedOrder));
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        logger.info("Fetching all orders");

        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.of(Optional.ofNullable(orders));
    }

    // Get order by id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {

        logger.info("Fetching order by id: {}", id);

        Order order = orderService.getOrderById(id);

        return ResponseEntity.of(Optional.ofNullable(order));
    }

    // Update order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @RequestBody Order order) {

        logger.info("Updating order");

        Order updatedOrder = orderService.updateOrder(id, order);

        return ResponseEntity.of(Optional.ofNullable(updatedOrder));
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        logger.info("Deleting order");

        orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }
}