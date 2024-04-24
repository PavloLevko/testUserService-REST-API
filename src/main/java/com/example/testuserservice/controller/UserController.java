package com.example.testuserservice.controller;

import com.example.testuserservice.entity.User;
import com.example.testuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody User user){
     return userService.createUser(user);
    }
    @GetMapping(value = "/users")
    public List<User> getAllUser(){
       return userService.getAll();
    }
}
