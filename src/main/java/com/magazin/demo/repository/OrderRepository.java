package com.magazin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magazin.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
