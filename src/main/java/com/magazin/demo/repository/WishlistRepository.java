package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;
import java.util.Set;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Modifying
    @Transactional
    @Query(value = "Update Wishlist set \"products\"=:newProducts where \"customer_id_id\"=:customerId", nativeQuery = true)
    void updateWishlist(@Param("customerId") Integer customerId,@Param("newProducts") Set<Product> products);

}
