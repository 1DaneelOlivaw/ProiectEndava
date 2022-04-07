package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Order;
import com.magazin.demo.repository.OrderRepository;
import com.magazin.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException((String.format("the order with id %s could not be found", orderId))));
    }

    @Override
    public Order updateOrderStatus(Order order, String status) {
        order.setStatus(status);
        order.setLastModified(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order searchedOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException((String.format("orders with id %s could not be found", orderId))));
        orderRepository.delete(searchedOrder);
    }

    @Override
    public Order saveOrder(Order order) {
        return  orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
