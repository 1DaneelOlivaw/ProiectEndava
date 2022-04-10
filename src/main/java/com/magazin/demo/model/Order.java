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
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @JsonIgnore
    @OneToOne
    private User userId;

    @JsonIgnore
    @ManyToMany()
    private Set<Product> orderProducts;

    private String status;


    public Order(Date lastModified, User userId, Set<Product> orderProducts, String status) {
        this.lastModified = lastModified;
        this.userId = userId;
        this.orderProducts = orderProducts;
        this.status = status;
    }

    public Order(Date lastModified, User userId, String status) {
        this.lastModified = lastModified;
        this.userId = userId;
        this.status = status;
    }
}
