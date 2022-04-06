package com.magazin.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "principle_groups")
@Getter
@Setter
@NoArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "userGroups")
    private Set<User> users;

    public UserGroup(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
