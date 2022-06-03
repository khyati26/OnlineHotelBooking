package com.testspring.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Hotel {

    @NotNull(message = "id must not be null")
    private int id;

    @NotNull(message = "name must not be null")
    @Size(min = 3, max = 5, message = "name must be greater than 3")
    private String name;

    @Email(message = "email address must be a well-formed")
    private String email;

    @NotNull(message = "mobile must not be null")
    @Min(10)
    private String mobile;

    @NotNull(message = "address must not be null")
    @Valid
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
