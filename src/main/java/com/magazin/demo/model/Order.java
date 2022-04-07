package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @JsonIgnore
    @OneToOne
    private Customer customerId;

    @JsonIgnore
    @ManyToMany()
    private Set<Product> orderProducts;

    private String status;


    public Order(Date lastModified, Customer customerId, Set<Product> orderProducts, String status) {
        this.lastModified = lastModified;
        this.customerId = customerId;
        this.orderProducts = orderProducts;
        this.status = status;
    }

    public Order(Date lastModified, Customer customerId, String status) {
        this.lastModified = lastModified;
        this.customerId = customerId;
        this.status = status;
    }
}
