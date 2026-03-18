package com.restaurant.serviceImpl;

import com.restaurant.entity.OrderItem;
import com.restaurant.repository.OrderItemRepository;
import com.restaurant.service.OrderItemService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    // Add item to order
    @Override
    public OrderItem addItem(OrderItem item) {

        logger.info("Adding item to orderId: {}", item.getOrderId());

        return orderItemRepository.save(item);
    }

    // Get items by orderId
    @Override
    public List<OrderItem> getItemsByOrderId(Long orderId) {

        logger.info("Fetching items for orderId: {}", orderId);

        return orderItemRepository.findByOrderId(orderId);
    }

    // Update item
    @Override
    public OrderItem updateItem(Long id, OrderItem updatedItem) {

        logger.info("Updating order item with id: {}", id);

        Optional<OrderItem> existing = orderItemRepository.findById(id);

        if (existing.isPresent()) {

            OrderItem item = existing.get();

            item.setMenuItemId(updatedItem.getMenuItemId());
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());

            return orderItemRepository.save(item);
        }

        logger.warn("OrderItem not found with id: {}", id);
        return null;
    }

    // Delete item
    @Override
    public void deleteItem(Long id) {

        logger.info("Deleting order item with id: {}", id);

        orderItemRepository.deleteById(id);
    }
}