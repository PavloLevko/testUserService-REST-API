package com.example.testuserservice.service;

import com.example.testuserservice.entity.User;
import com.example.testuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

public Long createUser (User user){
   return userRepository.save(user).getId();
}

}
