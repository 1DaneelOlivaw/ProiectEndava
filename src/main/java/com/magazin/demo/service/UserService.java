package com.magazin.demo.service;

import com.magazin.demo.model.User;

public interface UserService {
/*
        Customer getCustomer(int userId);
        Customer deleteCustomer(int userId);
        Customer updateCustomer(int userId);*/

        User getUserById(Integer id);

        public void register(User user);

        boolean checkIfUserExist(String username);
}
