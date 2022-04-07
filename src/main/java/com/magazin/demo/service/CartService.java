package com.magazin.demo.service;

import com.magazin.demo.model.Cart;

import java.util.Optional;


public interface CartService {

    Optional<Cart> getUserCart(int userId);
    Cart saveChanges(Cart cart);
    void deleteCart(Cart cart);
}
