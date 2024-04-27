package com.example.testuserservice.dto;

import jakarta.validation.constraints.*;
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
    @NotNull(message = "This field doesn't be blank!")
    private Date birthData;
    @Min(value = 8, message = "Wrong input phone number! Please input min 10 numbers.")
    private int phoneNumber;
}
