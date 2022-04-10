package com.magazin.demo.controller;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.User;
import com.magazin.demo.model.Wishlist;
import com.magazin.demo.service.ProductService;
import com.magazin.demo.service.UserService;
import com.magazin.demo.service.WishlistService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(value ="Access to customers wishlist", tags = "/wishlists")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    private final ProductService productService;
    private final UserService userService;

    @ApiOperation(value = "Find all wishlists", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Wishlist> getAllWishlists() {
        return wishlistService.findAll();
    }


    @ApiOperation(value = "Find wishlist by username", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Wishlist found!"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @GetMapping("/{username}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable String username){
     User user = userService.getUser(username);
     Wishlist wishlistById = wishlistService.getWishlist(username);
     return new ResponseEntity<>(wishlistById, HttpStatus.OK);
    }

    private Wishlist getInitialWishlist(String username) {
        if (wishlistService.getWishlist(username) != null) {
            return wishlistService.getWishlist(username);
        } else {
            return new Wishlist(userService.getUser(username));
        }
    }

    @ApiOperation(value = "Add a product to the wishlist", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New product added to the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Something went wrong, products not found") })
    @PutMapping("/{username}/{productName}")
    public ResponseEntity<Wishlist> addProductByName(@PathVariable String username, @PathVariable String productName) {
       User user = userService.getUser(username);
       Wishlist initialWishlist = getInitialWishlist(username);
       Product newProduct = productService.getProductByName(productName);
       Set<Product> productsInWishlist = initialWishlist.getProducts();
       productsInWishlist.add(newProduct);
       initialWishlist.setProducts(productsInWishlist);
       wishlistService.saveChanges(initialWishlist);
       return new ResponseEntity<>(wishlistService.getWishlist(username), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product from the wishlist", authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product removed from the wishlist"),
            @ApiResponse(code = 401, message = "Login needed before performing this operation"),
            @ApiResponse(code = 403, message = "User does not have enough privileges"),
            @ApiResponse(code = 404, message = "Product not found") })
    @DeleteMapping("/{username}/{productName}")
    public ResponseEntity<Wishlist> deleteProductByName(@PathVariable String username, @PathVariable String productName)
    {
        User user = userService.getUser(username);
        Wishlist initialWishlist = wishlistService.getWishlist(username);
        Product newProduct = productService.getProductByName(productName);
        Set<Product> productSet = initialWishlist.getProducts();
        if (!productSet.contains(newProduct)) {
            return new ResponseEntity<>(wishlistService.getWishlist(username), HttpStatus.NOT_FOUND);
        }
        productSet.remove(newProduct);
        initialWishlist.setProducts(productSet);
        wishlistService.saveChanges(initialWishlist);
        return new ResponseEntity<>(wishlistService.getWishlist(username), HttpStatus.OK);
    }




}
