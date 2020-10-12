package ru.requests_registration.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.requests_registration.auth.dto.UserDto;
import ru.requests_registration.auth.mapper.UserMapper;
import ru.requests_registration.auth.model.User;
import ru.requests_registration.auth.repository.UserJpaRepository;
import ru.requests_registration.auth.service.interfaces.UserService;
import ru.requests_registration.common.CommonUtils;
import ru.requests_registration.common.dto.ResponseObjectDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository,
                           UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;

        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<ResponseObjectDto> getAllUserList() {
        try {
            List<User> userList = userJpaRepository.findAll();
            return CommonUtils.returnDefaultGoodResponseWithObject(userMapper.convertToListUserDto(userList));
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка получения списка пользователей");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> setUserStatusToOperatorByUserId(Integer userId) {
        try {
            userJpaRepository.setOperatorRoleToUserByUserId(userId);
            return CommonUtils.returnDefaultGoodResponseWithObject(userMapper.convert(userJpaRepository.getById(userId), UserDto.class));
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка изменения статуса пользователя");
        }
    }
}
