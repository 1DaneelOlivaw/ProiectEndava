/*
package com.magazin.demo.service.impl;

import com.magazin.demo.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationServiceImpl {

    private final UserServiceImpl userService;
    private final VendorServiceImpl vendorService;
    private final CustomerServiceImpl customerService;
    private final EmailValidatorImpl emailValidator;

    public String registerAdmin(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }

   /public String registerCustomer(RegistrationRequest request) {
       boolean isValidEmail = emailValidator.test(request.getEmail());
       if(!isValidEmail) {
           throw new IllegalStateException("email not valid");
       }

       return customerService.signUpUser(
               new Customer(
                       request.getFirstName(),
                       request.getLastName(),
                       request.getEmail(),
                       request.getPassword(),
                       request.getAddress()
               )
       );
    }

    public String registerVendor(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return vendorService.signUpVendor(
                new Vendor(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getVendorName(),
                        request.getAddress(),
                        UserRole.ROLE_VENDOR
                )
        );
    }
}
*/
