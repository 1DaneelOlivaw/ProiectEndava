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
    private Long id;

    @JsonIgnore
    @OneToOne
    private User userId;

    @ManyToMany(cascade = {
            CascadeType.PERSIST
    })
    @Column(name = "products")
    private Set<Product> products;

    public Wishlist(User user) {
        this.userId = user;
    }

    public Wishlist(User userId, Set<Product> products) {
        this.userId = userId;
        this.products = products;
    }
}
