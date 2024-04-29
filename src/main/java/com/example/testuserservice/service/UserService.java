package com.example.testuserservice.service;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.exception.ApiRequestException;
import com.example.testuserservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    @Value("${targetAge}")
    private int targetAge;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Long createUser(UserDto user) {
        validationUserAge(user);
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
        if(userDto.getEmail()!=null){
            userOptional.get().setEmail(userDto.getEmail());
        }
        if(userDto.getBirthData()!=null){
            userOptional.get().setBirthData(userDto.getBirthData());
        }
        if (userDto.getFirstName()!=null){
            userOptional.get().setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName()!=null){
            userOptional.get().setLastName(userDto.getLastName());
        }
        else if (userDto.getPhoneNumber()!=0){
            userOptional.get().setPhoneNumber(userDto.getPhoneNumber());
        }
        User user = new User();
        user = userOptional.get();


        userRepository.save(user);
    }


    public void deleted(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByDataRange(Date from, Date to) {
        return userRepository.findByDataRange(from, to);
    }

    public void validationUserAge(UserDto user) {
        Date birthDate = user.getBirthData();
        LocalDate birthDateLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        int years = Period.between(birthDateLocal, now).getYears();

        if (years < targetAge) {
            throw new ApiRequestException("Only for 18 and older");
        }
    }




}
