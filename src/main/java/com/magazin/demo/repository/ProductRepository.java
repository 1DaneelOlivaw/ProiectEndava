package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT FROM products p WHERE p.name = :product_name", nativeQuery = true)
    Optional<Product> getProductByName(@Param("product_name") String name);

    @Modifying
    @Query(value = "DELETE FROM products p WHERE p.id = :productId", nativeQuery = true)
    void deleteProduct(@Param("productId") int id);
}
