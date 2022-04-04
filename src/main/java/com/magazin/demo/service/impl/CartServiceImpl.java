package com.magazin.demo.service.impl;

import com.magazin.demo.model.Cart;
import com.magazin.demo.repository.CartRepository;
import com.magazin.demo.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){ this.cartRepository = cartRepository; }

    @Override
    public Cart addCartItem() {
        return null;
    }

    @Override
    public Cart deleteCartItem() {
        return null;
    }
}
