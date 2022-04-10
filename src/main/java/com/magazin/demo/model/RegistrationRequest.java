package com.magazin.demo.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String password;

    private  String address;

    private String vendorName;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String firstName, String lastName, String email, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
