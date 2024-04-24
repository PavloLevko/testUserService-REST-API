package com.example.testuserservice.controller;

import com.example.testuserservice.entity.User;
import com.example.testuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
     return userService.getById(id);
    }

    @PostMapping(value = "/users/user")
    public Long updateUser (@RequestBody User user){
      return userService.update(user);
    }
}
