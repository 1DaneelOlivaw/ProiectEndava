package com.magazin.demo.model;

import lombok.Getter;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id")
    private Integer userId;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;

    private Float totalPrice;

    private Integer totalItems;
}
