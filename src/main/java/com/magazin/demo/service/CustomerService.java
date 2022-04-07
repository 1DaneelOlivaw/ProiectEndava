package com.magazin.demo.service;

import com.magazin.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomer(int userId);

    void deleteCustomer(Customer customer);

    Customer updateCustomer(int userId, Customer customer);

    List<Customer> findAll();
}
