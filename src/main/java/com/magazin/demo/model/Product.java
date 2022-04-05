package com.magazin.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Float price;

    private Boolean stock;

    public Product(String name, Float price, Boolean stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }
}
