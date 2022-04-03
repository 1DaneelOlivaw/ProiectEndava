package com.magazin.demo.repository;

import com.magazin.demo.model.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, ID> {

  //  @Query("blabla")
    User findById(Integer id);

}
