package com.magazin.demo.controller;

import com.magazin.demo.model.Customer;
import com.magazin.demo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Access to store customers", tags = "/customers")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }


    @ApiOperation(value = "Find customer by ID")
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {
        Optional<Customer> customerById = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customerById.get(), HttpStatus.OK);
    }
    @ApiOperation(value =  "Delete customer by ID")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
        Customer customerById = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(customerById, HttpStatus.OK);
    }
    @ApiOperation(value = "Update customer by ID")
    @PutMapping()
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer.getId());
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
