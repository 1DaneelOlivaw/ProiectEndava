package com.magazin.demo.service;

import com.magazin.demo.model.Order;

public interface OrderService {

    Order getOrdersByUserId(int userId);

    Order addNewOrder(int userId, int cartId);

    Order updateOrder(Order order);

    void deleteOrder(Order order);
}
