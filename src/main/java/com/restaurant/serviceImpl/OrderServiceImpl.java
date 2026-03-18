package com.restaurant.serviceImpl;

import com.restaurant.entity.Order;
import com.restaurant.entity.OrderStatus;
import com.restaurant.repository.OrderRepository;
import com.restaurant.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    // Create new order
    @Override
    public Order createOrder(Order order) {

        logger.info("Creating new order");

        // Set default values
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    // Get all orders
    @Override
    public List<Order> getAllOrders() {

        logger.info("Fetching all orders");

        return orderRepository.findAll();
    }

    // Get order by ID
    @Override
    public Order getOrderById(Long id) {

        logger.info("Fetching order with id: {}", id);

        Optional<Order> order = orderRepository.findById(id);

        return order.orElse(null);
    }

    // Update order
    @Override
    public Order updateOrder(Long id, Order updatedOrder) {

        logger.info("Updating order with id: {}", id);

        Optional<Order> existing = orderRepository.findById(id);

        if (existing.isPresent()) {

            Order order = existing.get();

            order.setCustomerId(updatedOrder.getCustomerId());
            order.setTableId(updatedOrder.getTableId());
            order.setStatus(updatedOrder.getStatus());

            return orderRepository.save(order);
        }

        logger.warn("Order not found with id: {}", id);
        return null;
    }

    // Delete order
    @Override
    public void deleteOrder(Long id) {

        logger.info("Deleting order with id: {}", id);

        orderRepository.deleteById(id);
    }
}