package com.magazin.demo.service.impl;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.User;
import com.magazin.demo.repository.CartRepository;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Cart> getUserCart(String username) {
        User user = userRepository.findByUsername(username);
        return cartRepository.getCartByCustomerId(user.getId());
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
