package com.magazin.demo.service.impl;

import com.magazin.demo.model.Customer;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {
    public CustomerServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Customer getCustomer(int userId) {
        return null;
    }

    @Override
    public Customer deleteCustomer(int userId) {
        return null;
    }

    @Override
    public Customer updateCustomer(int userId) {
        return null;
    }
}
