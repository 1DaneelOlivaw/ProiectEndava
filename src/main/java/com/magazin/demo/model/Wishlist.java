package com.magazin.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="wishlist")
@Getter
@Setter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToMany
    @JoinColumn(name = "products")
    private Set<Product> products;
}
