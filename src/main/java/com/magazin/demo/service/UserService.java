package com.magazin.demo.service;

import com.magazin.demo.model.User;

public interface UserService {
/*
        Customer getCustomer(int userId);
        Customer deleteCustomer(int userId);
        Customer updateCustomer(int userId);*/

        User getUserById(Integer id);

        org.springframework.security.core.userdetails.User loadUserById(Integer id);
}
