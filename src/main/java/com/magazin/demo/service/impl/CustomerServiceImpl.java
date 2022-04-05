package com.magazin.demo.service.impl;

import com.magazin.demo.model.Customer;
import com.magazin.demo.repository.CustomerRepository;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository customerRepository) {
        super(userRepository);
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
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
