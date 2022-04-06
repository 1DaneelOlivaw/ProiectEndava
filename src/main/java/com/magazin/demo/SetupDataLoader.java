package com.magazin.demo;

import com.magazin.demo.model.*;
import com.magazin.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        UserGroup customerGroup = new UserGroup("customer","CUSTOMER");
        userGroupRepository.save(customerGroup);
        UserGroup adminGroup = new UserGroup("admin","ADMIN");
        userGroupRepository.save(adminGroup);

        User admin = new User();
        admin.setUsername("myAdmin");
        admin.setPhoneNumber(0707);
        admin.setAddress("myAddress");
        admin.setPassword(passwordEncoder.encode("myPassword"));
        admin.setUserGroups(new HashSet<UserGroup>(Arrays.asList(adminGroup)));
        userRepository.save(admin);

        Product product = new Product("productA", 30f, true);
        productRepository.save(product);
        Product product2 = new Product("productB", 50f, true);
        productRepository.save(product2);

        Customer customer = new Customer();
        customer.setUsername("danielb");
        customer.setPhoneNumber(0404);
        customer.setAddress("myAddress");
        customer.setPassword(passwordEncoder.encode("proiectEndava"));
        customer.setFirstName("Daniel");
        customer.setLastName("Burlacu");
        customer.setUserGroups(new HashSet<UserGroup>(Arrays.asList(customerGroup)));
        customerRepository.save(customer);
        //Wishlist wishlist = new Wishlist(customer, new HashSet<>(Arrays.asList(product)));
        Wishlist wishlist = new Wishlist(customer);
        wishlistRepository.save(wishlist);

        alreadySetup = true;
    }
}
