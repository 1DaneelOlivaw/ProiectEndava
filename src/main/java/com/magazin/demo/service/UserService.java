package com.magazin.demo.service;

import com.magazin.demo.model.User;
import com.sun.xml.bind.v2.model.core.ID;

public interface UserService {
    User getUserById(Integer id);
    org.springframework.security.core.userdetails.User loadUserById(Integer id);
}
