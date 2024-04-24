package com.example.testuserservice.service;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public Long createUser(UserDto user) {
        return userRepository.save(mapper.map(user, User.class)).getId();
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Long update(User user) {
        return userRepository.save(user).getId();
    }
    public void deleted(Long id){
        userRepository.deleteById(id);
    }


}
