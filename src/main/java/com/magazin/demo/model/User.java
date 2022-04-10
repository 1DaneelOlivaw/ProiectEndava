package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue (
           strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    private String address;

    @JsonIgnore
    @OneToOne(mappedBy = "userId")
    private Wishlist wishlist;

    @JsonIgnore
    @OneToOne(mappedBy = "userId")
    private Cart cart;

    @JsonIgnore
    @OneToOne(mappedBy = "userId")
    private Order order;

    @JsonIgnore
    @OneToMany
    private Set<Product> products;


    public User(Long id, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String firstName, String lastName, String username, String password, Collection<Role> roles, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.address = address;
    }

    public String getUsername() {

        return username;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }



}
