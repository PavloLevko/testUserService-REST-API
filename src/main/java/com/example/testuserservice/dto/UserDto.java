package com.example.testuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserDto {

    private Long id;
    @Email(message = "Wrong email")
    private String email;
    @Size(min = 2, max = 30, message = "Wrong input firstname! Please input min 2 chars.")
    private String firstName;
    @Size(min = 2, max = 30, message = "Wrong input lastname! Please input min 2 chars.")
    private String lastName;
    @NotNull(message = "This field doesn't be blank!")
    private Date birthData;
    @Min(value = 8, message = "Wrong input phone number! Please input min 10 numbers.")
    private int phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthData() {
        return birthData;
    }

    public void setBirthData(Date birthData) {
        this.birthData = birthData;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}