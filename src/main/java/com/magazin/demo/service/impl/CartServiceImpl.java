package com.magazin.demo.service.impl;

import com.magazin.demo.model.Cart;
import com.magazin.demo.repository.CartRepository;
import com.magazin.demo.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getUserCart(int userId) {
        return cartRepository.getCartByCustomerId(userId);
    }

    @Override
    public Cart saveChanges(Cart cart) {
        cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

}
