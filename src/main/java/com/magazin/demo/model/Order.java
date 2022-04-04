package com.magazin.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id")
    private Integer userId;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;

    private String status;





}
