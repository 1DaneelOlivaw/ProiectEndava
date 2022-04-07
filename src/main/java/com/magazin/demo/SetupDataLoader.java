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
    private CartRepository cartRepository;

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
        admin.setPhoneNumber("0707707070");
        admin.setAddress("myAddress");
        admin.setPassword(passwordEncoder.encode("myPassword"));
        admin.setUserGroups(new HashSet<UserGroup>(List.of(adminGroup)));
        userRepository.save(admin);

        User admin1 = new User();
        admin1.setUsername("myAdmin1");
        admin1.setPhoneNumber("0707707071");
        admin1.setAddress("myAddress");
        admin1.setPassword(passwordEncoder.encode("myPassword1"));
        admin1.setUserGroups(new HashSet<UserGroup>(List.of(adminGroup)));
        userRepository.save(admin1);

        Product product = new Product("productA", 30f, true);
        productRepository.save(product);
        Product product2 = new Product("productB", 50f, true);
        productRepository.save(product2);
        Product product3 = new Product("productC", 65.5f, true);
        productRepository.save(product3);
        Product product4 = new Product("productD", 10f, false);
        productRepository.save(product4);

        Customer customer = new Customer();
        customer.setUsername("willSlappin");
        customer.setPhoneNumber("0740869966");
        customer.setAddress("myAddress");
        customer.setPassword(passwordEncoder.encode("Willy"));
        customer.setFirstName("Will");
        customer.setLastName("Smith");
        customer.setUserGroups(new HashSet<UserGroup>(List.of(customerGroup)));
        customerRepository.save(customer);
        Wishlist wishlist = new Wishlist(customer);
        wishlistRepository.save(wishlist);
        Cart cart = new Cart(customer,0f,0);
        cartRepository.save(cart);

        Customer customer1 = new Customer();
        customer1.setUsername("chrisCryin");
        customer1.setPhoneNumber("0740587666");
        customer1.setAddress("myAddress");
        customer1.setPassword(passwordEncoder.encode("Chrissy"));
        customer1.setFirstName("Chris");
        customer1.setLastName("Rock");
        customer1.setUserGroups(new HashSet<UserGroup>(List.of(customerGroup)));
        customerRepository.save(customer1);
        Wishlist wishlist1 = new Wishlist(customer1);
        wishlistRepository.save(wishlist1);

        Customer customer2 = new Customer();
        customer2.setUsername("denzelWatchin");
        customer2.setPhoneNumber("0740587634");
        customer2.setAddress("myAddress");
        customer2.setPassword(passwordEncoder.encode("Denzel"));
        customer2.setFirstName("Denzel");
        customer2.setLastName("Washington");
        customer2.setUserGroups(new HashSet<UserGroup>(List.of(customerGroup)));
        customerRepository.save(customer2);
        Wishlist wishlist2 = new Wishlist(customer2);
        wishlistRepository.save(wishlist2);

        alreadySetup = true;
    }
}
