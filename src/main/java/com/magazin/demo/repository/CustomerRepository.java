package com.magazin.demo.repository;

import com.magazin.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    //@Query("")
    Optional<Customer> getCustomerByLastName(String lastName);

    //@Query("")
    Optional<Customer> getCustomerByFirstName(String firstName);
}
