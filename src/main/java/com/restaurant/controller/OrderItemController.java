package com.restaurant.controller;

import com.restaurant.entity.OrderItem;
import com.restaurant.service.OrderItemService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    // Add item
    @PostMapping
    public ResponseEntity<OrderItem> addItem(@RequestBody OrderItem item) {

        logger.info("Adding new order item");

        OrderItem saved = orderItemService.addItem(item);

        return ResponseEntity.of(Optional.ofNullable(saved));
    }

    // Get items by orderId
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItem>> getItems(@PathVariable Long orderId) {

        logger.info("Fetching order items");

        List<OrderItem> items = orderItemService.getItemsByOrderId(orderId);

        return ResponseEntity.of(Optional.ofNullable(items));
    }

    // Update item
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateItem(
            @PathVariable Long id,
            @RequestBody OrderItem item) {

        logger.info("Updating order item");

        OrderItem updated = orderItemService.updateItem(id, item);

        return ResponseEntity.of(Optional.ofNullable(updated));
    }

    // Delete item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {

        logger.info("Deleting order item");

        orderItemService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }
}