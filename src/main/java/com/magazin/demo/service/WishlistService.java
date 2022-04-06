package com.magazin.demo.service;

import com.magazin.demo.model.Customer;
import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;

import java.util.Set;

public interface WishlistService {
    Wishlist getWishlist(int userId);
    Wishlist addProductById(int userId, Product product);
    Wishlist deleteProductById(int userId, Product product);
    void updateWishlist(int user, Set<Product> products);
    Wishlist saveChanges(Wishlist wishlist);
}
