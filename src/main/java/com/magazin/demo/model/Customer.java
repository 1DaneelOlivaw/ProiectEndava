package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends User {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @JsonIgnore
    @OneToOne(mappedBy = "customerId")
    private Wishlist wishlist;

    @JsonIgnore
    @OneToOne(mappedBy = "customerId")
    private Cart cart;

    @JsonIgnore
    @OneToOne(mappedBy = "customerId")
    private Order order;
}
