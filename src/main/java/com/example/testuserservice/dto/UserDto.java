package com.example.testuserservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class UserDto {

    private Long id;
    @Email(message = "Wrong email")
    private String email;
    @Size(min = 2, max = 30, message = "Wrong input firstname! Please input min 2 chars.")
    private String firstName;
    @Size(min = 2, max = 30, message = "Wrong input lastname! Please input min 2 chars.")
    private String lastName;
    @NotBlank(message = "This field doesn't be blank!")
    private Date birthData;
    @Size(min = 10, max = 14, message = "Wrong input phone number! Please input min 10 numbers.")
    private int phoneNumber;
}
