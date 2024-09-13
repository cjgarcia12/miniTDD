package org.example.service;

import org.example.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);             // Create
    Optional<Order> getOrderById(Long id);    // Read
    List<Order> getAllOrders();               // Read all
    Order updateOrder(Long id, Order order);  // Update
    void deleteOrder(Long id);                // Delete
}

