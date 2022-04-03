package com.magazin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.magazin.demo.model.Order;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  // @Query("blabla")
    Optional<Order> findByUserId(String userID);
}
