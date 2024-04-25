package com.example.testuserservice.controller;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.exception.ApiRequestException;
import com.example.testuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    public Long createUser(@RequestBody @Validated UserDto user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        List<User> allUsers = userService.getAll();
        if(allUsers.isEmpty()){
            throw new ApiRequestException("Can't get all Users.");
        }
        return allUsers;
    }


    @GetMapping(value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        Optional<User> userbyId = userService.getById(id);
        if (userbyId.isEmpty()){
            throw new ApiRequestException("Can't get User with id:" + id);
        }
        return userbyId;
    }

    @PostMapping(value = "/users/user")
    public Long updateUser(@RequestBody @Validated User user) {
        return userService.update(user);
    }
    @DeleteMapping(value = "/users/{id}")
    public void deletedUserById(@PathVariable Long id){
        userService.deleted(id);
    }
}
