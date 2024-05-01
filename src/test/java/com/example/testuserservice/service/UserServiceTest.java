package com.example.testuserservice.service;

import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import com.example.testuserservice.exception.ApiRequestException;
import com.example.testuserservice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDto userDto;
    @Value("${targetAge}")
    private int targetAge;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

  @Test
  public void createdUserTest(){
      User user = testUsersList().get(1);
      userDto.setEmail(user.getEmail());
      userDto.setPhoneNumber(user.getPhoneNumber());
      userDto.setLastName(user.getLastName());
      userDto.setBirthData(user.getBirthData());


      when(mapper.map(userDto, User.class)).thenReturn(user);
      when(userRepository.save(user)).thenReturn(user);

      UserService spyService = spy(userService);

      doNothing().when(spyService).validationUserAge(userDto);

      Long userId = spyService.createUser(userDto);

      Assertions.assertThat(userId).isNotNull();
      verify(userRepository, times(1)).save(user);
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
  @Test
  public void updateUserTest(){
      User user = testUsersList().get(1);
      user.setId(1L);
      user.setFirstName("John");
      user.setLastName("Doe");
      user.setEmail("john.doe@example.com");

      when(userRepository.save(any())).thenReturn(user);
      Long afterUpdate = userService.update(user);

      Assertions.assertThat(afterUpdate).isNotNull();
      Assertions.assertThat(afterUpdate).isEqualTo(user.getId());
  }

  @Test
  public void deletedUserTest(){
      long userId = 1;
      userService.deleted(1L);
      verify(userRepository, times(1)).deleteById(userId);
  }
  @Test
  public void findByRangeTest(){
      Date fromDate = new Date(2022, 3, 1);
      Date toDate = new Date(2022, 3, 15);
      List<User> expectedUsers = testUsersList();

      when(userRepository.findByDataRange(fromDate, toDate)).thenReturn(expectedUsers);


      List<User> actualUsers = userService.findByDataRange(fromDate, toDate);

      Assertions.assertThat(actualUsers).isNotNull();
      Assertions.assertThat(actualUsers).hasSize(expectedUsers.size());
      Assertions.assertThat(actualUsers).containsExactlyElementsOf(expectedUsers);
  }

  @Test
  public void validationUserAgeTest() {
      Date birthDate = new Date(1990, 1, 1);

      when(userDto.getBirthData()).thenReturn(birthDate);

      Throwable exception = assertThrows(ApiRequestException.class, () -> userService.validationUserAge(userDto));

      String expectedErrorMessage = "Only for" + targetAge + "and older";
      String actualErrorMessage = exception.getMessage();
      Assertions.assertThat(expectedErrorMessage).isEqualTo(actualErrorMessage);
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
