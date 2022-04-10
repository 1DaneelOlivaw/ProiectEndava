package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Modifying
    @Transactional
    @Query(value = "Update Wishlist set \"products\"=:newProducts where \"user_id_id\"=:userId", nativeQuery = true)
    void updateWishlist(@Param("userId") Long userId,@Param("newProducts") Set<Product> products);

    @Query(value = "Select * from Wishlist where user_id_id=:userId", nativeQuery = true)
    Optional<Wishlist> getWishlistByUsername(@Param("userId") Long userId);

}
