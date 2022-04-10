package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select p From Product p where p.name=:name")
    Optional<Product> getProductByName( String name);

    @Modifying
    @Query(value = "DELETE FROM products p WHERE p.id = :productId", nativeQuery = true)
    void deleteProduct(@Param("productId") Long id);

    @Modifying
    @Query(value = "DELETE FROM products p WHERE p.name = :productName", nativeQuery = true)
    void deleteProductByName(@Param("productName") String productName);
}
