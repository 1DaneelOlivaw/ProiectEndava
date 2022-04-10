package com.magazin.demo;

import com.magazin.demo.model.*;
import com.magazin.demo.repository.*;
import com.magazin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;


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

        Product product = new Product("productA", 30f, true);
        productRepository.save(product);
        Product product2 = new Product("productB", 50f, true);
        productRepository.save(product2);
        Product product3 = new Product("productC", 65.5f, true);
        productRepository.save(product3);
        Product product4 = new Product("productD", 10f, false);
        productRepository.save(product4);

        alreadySetup = true;
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_VENDOR"));
            userService.saveRole(new Role(null, "ROLE_CUSTOMER"));

            User user1 = new User(null, "Jimothy" ,"Halpert", "jim", "1234", new ArrayList<>(), "Strada Melcilor");
            userService.saveUser(user1);
            userService.addRoleToUser("jim", "ROLE_CUSTOMER");
            wishlistRepository.save(new Wishlist(user1));
            cartRepository.save(new Cart(user1,0f,0));

            User user2 = new User(null, "Dwight"," Schrute", "dwight", "1234", new ArrayList<>(), "Strada Haiducilor");
            userService.saveUser(user2);
            userService.addRoleToUser("dwight", "ROLE_VENDOR");
            if (alreadySetup)
                user2.setProducts(new HashSet<Product>(Arrays.asList(productRepository.getProductByName("productA").get(),productRepository.getProductByName("productB").get())));


            userService.saveUser(new User(null, "Michael"," Scott", "dangerMike", "1234", new ArrayList<>(), "Strada Dumbravei"));
            userService.saveUser(new User(null, "Creed ","Bratton", "creed", "1234", new ArrayList<>(), "Strada Chefului"));

            //userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("dangerMike", "ROLE_ADMIN");
            userService.addRoleToUser("creed", "ROLE_CUSTOMER");
            userService.addRoleToUser("creed", "ROLE_VENDOR");
            userService.addRoleToUser("creed", "ROLE_ADMIN");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
