package com.magazin.demo.repository;

import com.magazin.demo.model.Role;
import com.magazin.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
