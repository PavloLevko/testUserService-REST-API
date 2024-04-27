package com.example.testuserservice.service;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.repository.UserRepository;
import com.example.testuserservice.service.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, ModelMapper mapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

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
        return userRepository.save(mapper.map(user, User.class)).getId();
    }


    public void partialUpdate(Long id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        userMapper.mapUserFromUserDto(userDto, user);
        userRepository.save(user);
    }


    public void deleted(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByDataRange(Date from, Date to) {
        return userRepository.findByDataRange(from, to);
    }

    public void validationUserAge(UserDto user) {

    }


}
