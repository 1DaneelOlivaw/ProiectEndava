package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cart")
@Setter
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne
    private Customer customerId;

    @JsonIgnore
    @ManyToMany()
    private Set<Product> cartProducts;

    private Float totalPrice;

    private Integer totalItems;

    public Cart(Customer customerId, Set<Product> cartProducts, Float totalPrice, Integer totalItems) {
        this.customerId = customerId;
        this.cartProducts = cartProducts;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    public Cart(Customer customerId, Float totalPrice, Integer totalItems) {
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }
}
