package com.example.testuserservice.service;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.exception.ApiException;
import com.example.testuserservice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
  @Test
    public void getAllUsersTest(){
      List<User> users = testUsersList();
    lenient().when(userRepository.findAll()).thenReturn(users);

      List<User> result = userService.getAll();

      Assertions.assertThat(result).isNotNull();
      Assertions.assertThat(result.size()).isEqualTo(2);
      Assertions.assertThat(result.get(1)).isInstanceOf(User.class);
  }

  @Test
    public void getByIdTest(){
      List<User> users = testUsersList();
      Optional<User> optionalUser = Optional.ofNullable(users.get(1));
      lenient().when(userRepository.findById(1L)).thenReturn(optionalUser);

      Optional<User> result = userService.getById(1L);

      Assertions.assertThat(result).isNotNull();
      Assertions.assertThat(result).isEqualTo(optionalUser);
      Assertions.assertThat(result).isInstanceOf(Optional.class);

  }

  private List<User> testUsersList(){
      User user1 = new User();
      User user2 = new User();
      Date date1 = new Date();
      Date date2 = new Date();
      date1.setTime(973836000000L);
      date2.setTime(973837000000L);

      user1.setId(1L);
      user1.setFirstName("Samuel");
      user1.setLastName("Altman");
      user1.setEmail("sam@gmail.com");
      user1.setBirthData(date1);
      user1.setPhoneNumber(12345678);

      user2.setId(2L);
      user2.setFirstName("Elon");
      user2.setLastName("Musk");
      user2.setEmail("Elon@Gmail.com");
      user2.setBirthData(date2);
      user2.setPhoneNumber(234567890);
      return List.of(user1, user2);
  }
}
