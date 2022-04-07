package com.magazin.demo.repository;

import com.magazin.demo.model.Cart;
import com.magazin.demo.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart, Integer> {
    //@Query("")
    Optional<Cart> getCartById(Integer id);

    //@Query(value = "select count(c)>0 from Cart c where c.customer_id_id=:customerId", nativeQuery = true)
    //boolean existsByCustomerId(@Param("customerId") Integer userId);

    //Boolean existsByCustomer(Integer userId);

    @Query("Select c From Cart c where c.customerId.id=:customerId")
    Optional<Cart> getCartByCustomerId(Integer customerId);
}
