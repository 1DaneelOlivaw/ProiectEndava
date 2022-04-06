package com.magazin.demo.service;

import com.magazin.demo.model.Order;

public interface OrderService {

    Order getOrdersByUserId(int userId);

    Order updateOrder(Order order);

    void deleteOrder(int orderId);

    Order saveOrder(int userId, int cartId);
}
