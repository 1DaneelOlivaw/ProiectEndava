package com.magazin.demo.repository;

import com.magazin.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    //@Query("")
    Optional<CartItem> getCartItemById (Integer id);
}
