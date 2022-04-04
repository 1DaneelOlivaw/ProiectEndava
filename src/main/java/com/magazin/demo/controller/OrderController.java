package com.magazin.demo.controller;

import com.magazin.demo.model.Order;
import com.magazin.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Acces to orders", tags = "/orders")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Find Order by userId")
    @GetMapping("{ordedrId}")
    public ResponseEntity<Order> getOrder(@PathVariable int orderId){
        Order orderById = orderService.getOrdersByUserId(orderId);
        return new ResponseEntity<>(orderById, HttpStatus.OK);
    }

    @ApiOperation(value = "")



    Order addNewOrder(int userId, int cartId);

    Order updateOrder(Order order);

    void deleteOrder(Order order);

}
