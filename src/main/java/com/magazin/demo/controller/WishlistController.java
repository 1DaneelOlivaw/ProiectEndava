package com.magazin.demo.controller;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import com.magazin.demo.service.WishlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value ="Acces to customers wishlist", tags = "/wishlists")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @ApiOperation(value = "Find wishilist by userID")
    @GetMapping("/{userId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable int userId){
     Wishlist wishlistById = wishlistService.getWishlist(userId);
     return new ResponseEntity<>(wishlistById, HttpStatus.OK);
    }
    @ApiOperation(value = "Add a product to the wishlist")
    @PostMapping()
    public ResponseEntity<Wishlist> addProductById(@RequestBody int userId,Product product){
        Wishlist savedWishlist = wishlistService.addProductById(userId,product);
        return new ResponseEntity<>(savedWishlist, HttpStatus.OK);

    }
    @ApiOperation(value = "Delete a product from the wishlist")
    @DeleteMapping()
    public ResponseEntity<Wishlist> deleteProductById(@RequestBody int userId,Product product)
    {
        Wishlist savedWishlist = wishlistService.deleteProductById(userId, product);
       return new ResponseEntity<>(savedWishlist, HttpStatus.OK);
    }




}
