package com.magazin.demo.repository;

import com.magazin.demo.model.Product;
import com.magazin.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    //@Query("")
    Optional<Wishlist> findById(Integer id);

}
