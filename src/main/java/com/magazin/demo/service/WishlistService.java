package com.magazin.demo.service;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;

public interface WishlistService {
    Wishlist getWishlist(int userId);
    Wishlist addProductById(int userId, Product product);
    Wishlist deleteProductById(int userId, Product product);

}
