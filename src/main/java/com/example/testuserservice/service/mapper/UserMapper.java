package com.example.testuserservice.service.mapper;
import com.example.testuserservice.dto.UserDto;
import com.example.testuserservice.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUserFromUserDto(UserDto dto, @MappingTarget User user);
}
