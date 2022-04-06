package com.magazin.demo.controller;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import com.magazin.demo.repository.CustomerRepository;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.CustomerService;
import com.magazin.demo.service.ProductService;
import com.magazin.demo.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(value ="Acces to customers wishlist", tags = "/wishlists")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    private final ProductService productService;
    private final CustomerService customerService;

    @ApiOperation(value = "Find wishilist by userID")
    @GetMapping("/{userId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable int userId){
     Wishlist wishlistById = wishlistService.getWishlist(userId);
     return new ResponseEntity<>(wishlistById, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a product to the wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PutMapping("/{userId}/{productName}")
    public ResponseEntity<Wishlist> addProductById(@PathVariable int userId,@PathVariable String productName)
    {
       Wishlist initalWishlist = wishlistService.getWishlist(userId);
       Product newProduct = productService.getProductByName(productName);
       Set<Product> productsInWishlist = initalWishlist.getProducts();
       productsInWishlist.add(newProduct);
       initalWishlist.setProducts(productsInWishlist);
       wishlistService.saveChanges(initalWishlist);
       //wishlistService.updateWishlist(userId, productsInWishlist);
       return new ResponseEntity<>(wishlistService.getWishlist(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product from the wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product removed from the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("/{userId}/{productName}")
    public ResponseEntity<Wishlist> deleteProductById(@PathVariable int userId,@PathVariable String productName)
    {
        Wishlist initialWishlist = wishlistService.getWishlist(userId);
        Product newProduct = productService.getProductByName(productName);
        Set<Product> productSet = initialWishlist.getProducts();
        productSet.remove(newProduct);
        initialWishlist.setProducts(productSet);
        wishlistService.saveChanges(initialWishlist);
        //wishlistService.updateWishlist(userId, productSet);
        return new ResponseEntity<>(wishlistService.getWishlist(userId), HttpStatus.OK);
    }




}
