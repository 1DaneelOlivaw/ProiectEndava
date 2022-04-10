package com.magazin.demo.service;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;

import java.util.List;
import java.util.Set;

public interface WishlistService {
    Wishlist getWishlist(String username);
    Wishlist addProductByName(String username, Product product);
    Wishlist deleteProductByName(String username, Product product);
    void updateWishlist(String username, Set<Product> products);
    Wishlist saveChanges(Wishlist wishlist);

    List<Wishlist> findAll();
}
