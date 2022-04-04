package com.magazin.demo.service;

import com.magazin.demo.model.Customer;

public interface CustomerService {

    Customer getCustomer(int userId);

    Customer deleteCustomer(int userId);

    Customer updateCustomer(int userId);
}
