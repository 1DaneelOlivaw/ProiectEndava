package com.magazin.demo.service;

import com.magazin.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomer(int userId);

    Customer deleteCustomer(int userId);

    Customer updateCustomer(int userId);

    List<Customer> findAll();
}
