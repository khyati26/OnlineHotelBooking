package com.testspring.validation;


import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

//import com.journaldev.spring.form.validator.Phone;

import java.util.Date;

public class Customer {

    private int id;
    @Size(min=2, max=30)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(18) @Max(100)
    private Integer age;

    @NotNull
    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthday;

//    @Phone
//    private String phone;

    public enum Gender {
        MALE, FEMALE
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
