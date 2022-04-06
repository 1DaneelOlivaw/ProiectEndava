package com.magazin.demo.service.impl;

import com.magazin.demo.exception.NotFoundException;
import com.magazin.demo.model.Cart;
import com.magazin.demo.repository.CartRepository;
import com.magazin.demo.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart(int userId) {
        return cartRepository.findById(userId).orElseThrow(
                () -> new NotFoundException((String.format("cart with userId %s could not be loaded", userId)))
        );
    }

    @Override
    public Cart saveChanges(Cart cart) {
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public Cart BuyCartItems(Cart cart) {
        cartRepository.saveAndFlush(cart);
        return cart;
    }
}
