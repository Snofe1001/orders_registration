package ru.requests_registration.auth.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.requests_registration.common.dto.ResponseObjectDto;

@Service
public interface UserService {

    ResponseEntity<ResponseObjectDto> getAllUserList();

    ResponseEntity<ResponseObjectDto> setUserStatusToOperatorByUserId(Integer userId);
}
