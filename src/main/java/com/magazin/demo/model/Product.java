package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    private Boolean stock;

    @ManyToOne
    //@JoinColumn(name = "vendor_id", insertable = false, updatable = false)
    private User vendor;

    @JsonIgnore
    @ManyToMany(mappedBy = "products",
                cascade = {
                    CascadeType.PERSIST})

    private Set<Wishlist> wishlists;

    public Product(String name, Float price, Boolean stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "orderProducts")
    private Set<Order> orders;

    @JsonIgnore
    @ManyToMany(mappedBy = "cartProducts")
    private Set<Cart> carts;

    public Product() {
    }
}
