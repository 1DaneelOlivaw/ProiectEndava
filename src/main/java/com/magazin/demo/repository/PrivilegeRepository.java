package com.magazin.demo.repository;

import com.magazin.demo.model.Privilege;
import com.magazin.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByName(String name);

}
