package com.restaurant.service;

import com.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem addItem(OrderItem item);

    List<OrderItem> getItemsByOrderId(Long orderId);

    OrderItem updateItem(Long id, OrderItem item);

    void deleteItem(Long id);
}