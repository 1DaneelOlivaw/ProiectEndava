package com.magazin.demo.service;

import com.magazin.demo.model.User;
import com.magazin.demo.model.Role;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    void deleteUser(String username);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
    User getUserById (Long id);
}
