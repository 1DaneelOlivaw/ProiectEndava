package com.magazin.demo.repository;

import com.magazin.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Integer> {
    //@Query("")
    Optional<Cart> getCartById(Integer id);

}
