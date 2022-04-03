package com.magazin.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String userType;

    @OneToOne(mappedBy = "userId")
    private Wishlist wishlist;

}
