package ru.requests_registration.auth.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.requests_registration.auth.dto.UserDto;
import ru.requests_registration.auth.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<UserDto> convertToListUserDto(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(convert(user, UserDto.class));
        }
        return userDtoList;
    }

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
