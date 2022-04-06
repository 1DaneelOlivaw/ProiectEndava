package com.magazin.demo.service;

import com.magazin.demo.model.Cart;


public interface CartService {

    Cart getCart(int userId);
    Cart saveChanges(Cart cart);
    Cart BuyCartItems(Cart cart);

}
