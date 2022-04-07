package com.magazin.demo.service.impl;

import com.magazin.demo.model.Customer;
import com.magazin.demo.repository.CustomerRepository;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Customer> getCustomer(int userId) {
        return customerRepository.getCustomerById(userId);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomer(int userId, Customer customer) {
        customer.setId(userId);
        customerRepository.save(customer);
        return customer;
    }
}
