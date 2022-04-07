package com.magazin.demo.repository;

import com.magazin.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    //@Query("")
    Optional<Customer> getCustomerByLastName(String lastName);

    //@Query("")
    Optional<Customer> getCustomerByFirstName(String firstName);

    Optional<Customer> getCustomerById(int userId);
}
