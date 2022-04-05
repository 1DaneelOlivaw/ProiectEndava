package com.magazin.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles;
}
