package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Order;
import com.magazin.demo.repository.OrderRepository;
import com.magazin.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrdersByUserId(int userId) {
        return orderRepository.findById(userId).orElseThrow(
                () -> new NotFoundException((String.format("orders with id %s could not be found", userId))));
    }

    @Override
    public Order addNewOrder(int userId, int cartId) {
        Order myOrder = new Order();
        myOrder.setUserId(userId);
        myOrder.setCartItems();
        return orderRepository.save(userId, cartId);
    }

    @Override
    public Order updateOrder(Order order) {
        Order savedOrder = getOrdersByUserId(order.getId());
        savedOrder.setStatus(Optional.ofNullable(order.getStatus()).orElse(savedOrder.getStatus()));

        return orderRepository.save(savedOrder);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
