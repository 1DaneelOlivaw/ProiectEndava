package com.magazin.demo.service.impl;

import com.magazin.demo.exception.UserAlreadyExistException;
import com.magazin.demo.model.User;
import com.magazin.demo.model.UserGroup;
import com.magazin.demo.repository.UserGroupRepository;
import com.magazin.demo.repository.UserRepository;
import com.magazin.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.*;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void register(User user) throws UserAlreadyExistException {
        if(checkIfUserExist(user.getUsername())){
            throw new UserAlreadyExistException("User already exists for this username");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        updateCustomerGroup(userEntity);
        userRepository.save(userEntity);
    }

    @Override
    public boolean checkIfUserExist(String username) {
        return userRepository.findUserByUsername(username)!=null ? true : false;
    }

    private void updateCustomerGroup(User user){
        UserGroup group= userGroupRepository.findByCode("customer");
        Set<UserGroup> newGroups = user.getUserGroups();
        newGroups.add(group);
        user.setUserGroups(newGroups);
    }
}
