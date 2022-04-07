package com.magazin.demo.service;

import com.magazin.demo.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(int orderId);

    Order updateOrderStatus(Order order, String status);

    void deleteOrder(int orderId);

    Order saveOrder(Order order);

    List<Order> findAll();
}
