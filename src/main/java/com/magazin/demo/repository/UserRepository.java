package com.magazin.demo.repository;

import com.magazin.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends  JpaRepository<User, Long>  {

  User findByUsername(String username);

  User getUserById(Long id);

}
