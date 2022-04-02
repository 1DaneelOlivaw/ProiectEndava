package com.magazin.demo.repository;

import com.magazin.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("blabla")
    Optional<User> findById(Integer id);
}
