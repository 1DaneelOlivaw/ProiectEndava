package com.magazin.demo.controller;

import com.magazin.demo.model.Order;
import com.magazin.demo.service.OrderService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Access to orders", tags = "/orders")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "Find all orders", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, orders not found") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @ApiOperation(value = "Find order by userId", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, order not found") })
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        Order orderById = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderById, HttpStatus.OK);
    }

    @ApiOperation(value = "Update an order", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, order could not be updated") })
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody String newStatus) {
        Order order = orderService.getOrderById(orderId);
        Order updateOrder = orderService.updateOrderStatus(order,newStatus);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @ApiOperation(value =  "Cancel order by ID", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, order could not be deleted") })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
