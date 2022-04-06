package com.magazin.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id")
    private Integer userId;

    @OneToMany(mappedBy = "products")
    private Set<Product> products;

    private Float totalPrice;

    private Integer totalItems;

    public Cart(Integer id, Integer userId, Set<Product> products, Float totalPrice, Integer totalItems) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }
}
