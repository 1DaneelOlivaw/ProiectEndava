package com.magazin.demo;

import com.magazin.demo.model.Customer;
import com.magazin.demo.model.Privilege;
import com.magazin.demo.model.Role;
import com.magazin.demo.model.User;
import com.magazin.demo.repository.PrivilegeRepository;
import com.magazin.demo.repository.RoleRepository;
import com.magazin.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

/*
    @Autowired
    private PasswordEncoder passwordEncoder;
*/

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setUsername("testUsername");
        admin.setPhoneNumber(0707);
        admin.setAddress("testAddress");
        admin.setPassword("testPassword");//passwordEncoder.encode("testPassword"));
        admin.setRoles(Arrays.asList(adminRole));
        userRepository.save(admin);

        Role customerRole = roleRepository.findByName("ROLE_USER");
        Customer customer = new Customer();
        customer.setUsername("danielb");
        customer.setPhoneNumber(0404);
        customer.setAddress("myAddress");
        customer.setPassword("proiectEndava");//passwordEncoder.encode("proiectEndava"));
        customer.setFirstName("Daniel");
        customer.setLastName("Burlacu");
        customer.setRoles(Arrays.asList(customerRole));
        userRepository.save(customer);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
