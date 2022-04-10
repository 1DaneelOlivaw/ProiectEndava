package com.magazin.demo.repository;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Long> {

    Optional<Cart> getCartById(Long id);

    @Query("Select c From Cart c where c.userId.id=:userId")
    Optional<Cart> getCartByCustomerId(Long userId);
}
