package com.magazin.demo.controller;

import com.magazin.demo.model.Order;
import com.magazin.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Acces to orders", tags = "/orders")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Find order by userId")
    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable int orderId){
        Order orderById = orderService.getOrdersByUserId(orderId);
        return new ResponseEntity<>(orderById, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new order")
    @PostMapping()
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(, order, );
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @ApiOperation(value = "Update an order")
    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updateOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @ApiOperation(value =  "Cancel order by ID")
    @DeleteMapping("/{orderId")
    public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
