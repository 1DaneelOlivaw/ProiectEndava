package com.magazin.demo.model;

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

    @OneToOne(mappedBy = "customerId")
    private Wishlist wishlist;

}
