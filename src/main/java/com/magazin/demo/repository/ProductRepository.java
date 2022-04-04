package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //@Query("")
    Optional<Product> getProductByName (String name);
}
