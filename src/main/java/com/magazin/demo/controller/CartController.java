package com.magazin.demo.controller;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.Order;
import com.magazin.demo.model.Product;
import com.magazin.demo.service.CartService;
import com.magazin.demo.service.CustomerService;
import com.magazin.demo.service.OrderService;
import com.magazin.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(value = "Access to customer cart", tags = "/cart")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
public class CartController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;

    @ApiOperation(value = "Find cart by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cart found!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping("{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable int userId) {
        Cart cartById = cartService.getCart(userId);
        return new ResponseEntity<>(cartById, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a product to the cart")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the cart"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PutMapping("/{useId}/{productName}")
    public ResponseEntity<Cart> addProductByName(@PathVariable int userId, @PathVariable String productName) {

        Cart initialCart = cartService.getCart(userId);
        Product newProduct = productService.getProductByName(productName);
        Set<Product> productsInCart = initialCart.getProducts();
        productsInCart.add(newProduct); if(newProduct.getStock()== true)
        initialCart.setProducts(productsInCart);
        cartService.saveChanges(initialCart);
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product from the cart")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product removed from the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("{/userId}/{productName}")
    public ResponseEntity<Cart> deleteProductByName(@PathVariable int userId, @PathVariable String productName) {
        Cart initialCart = cartService.getCart(userId);
        Product newProduct = productService.getProductByName(productName);
        Set<Product> productSet = initialCart.getProducts();
        productSet.remove(newProduct);
        initialCart.setProducts(productSet);
        cartService.saveChanges(initialCart);
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new order")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order placed!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @PutMapping("{/userId}/{CartId}")
    public ResponseEntity<Cart> saveCartAsOrder(@PathVariable int userId, @PathVariable int cartId){
        Cart cart = cartService.getCart(userId);
        Order order = orderService.saveOrder(userId, cartId);
        //save order
        //delete cart this cart
    }
}
