package com.magazin.demo.service;

import com.magazin.demo.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Long orderId);

    Order updateOrderStatus(Order order, String status);

    void deleteOrder(Long orderId);

    Order saveOrder(Order order);

    List<Order> findAll();
}
