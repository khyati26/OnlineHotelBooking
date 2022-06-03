package com.testspring.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

public class Address {
    private int id;

    @NotNull(message = "area must not be null")
    @Size(min = 3, max = 100, message = "area must be greater than 3")
    private String area;

    @NotNull(message = "state must not be null")
    @Size(min = 3, max = 200, message = "state must be greater than 3")
    private String state;

    @NotNull(message = "country must not be null")
    @Size(min = 3, max = 200, message = "country must be greater than 3")
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
