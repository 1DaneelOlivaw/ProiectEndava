package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="wishlist")
@Getter
@Setter
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne
    private Customer customerId;

    @ManyToMany(cascade = {
            CascadeType.PERSIST
    })
    @Column(name = "products")
    private Set<Product> products;

    public Wishlist(Customer customer) {
        this.customerId = customer;
    }

    public Wishlist(Customer customerId, Set<Product> products) {
        this.customerId = customerId;
        this.products = products;
    }
}
