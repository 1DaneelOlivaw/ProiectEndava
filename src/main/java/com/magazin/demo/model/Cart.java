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
    private Long id;

    @JsonIgnore
    @OneToOne
    private User userId;

    @JsonIgnore
    @ManyToMany()
    private Set<Product> cartProducts;

    private Float totalPrice;

    private Integer totalItems;

    public Cart(User userId, Set<Product> cartProducts, Float totalPrice, Integer totalItems) {
        this.userId = userId;
        this.cartProducts = cartProducts;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    public Cart(User userId, Float totalPrice, Integer totalItems) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }
}
