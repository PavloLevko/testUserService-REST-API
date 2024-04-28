package com.example.testuserservice.controller;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.exception.ApiRequestException;
import com.example.testuserservice.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
        Optional<User> userById = userService.getById(id);
        if (userById.isEmpty()){
            throw new ApiRequestException("Can't get User with id:" + id);
        }
        return userById;
    }

    @PostMapping(value = "/users/user")
    public Long updateUser(@RequestBody @Validated User user) {
        Long updateUser = userService.update(user);
        return updateUser;
    }
    @PatchMapping(value = "/users/user/{id}")
    public void partialUpdate (@PathVariable Long id, @RequestBody UserDto userDto){
        userService.partialUpdate(id, userDto);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deletedUserById(@PathVariable Long id){
        userService.deleted(id);
        if(userService.getById(id).isEmpty()){
            throw new ApiRequestException("User with id:" + id + " is absent.");
        }
    }
    @GetMapping(value ="/users/users/")
    public List<User> getUserByRangeData(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
                                         @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to){
     return userService.findByDataRange(from,to);
    }
}
