package ru.requests_registration.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.requests_registration.auth.service.interfaces.UserService;
import ru.requests_registration.common.dto.ResponseObjectDto;

@RestController
@Transactional
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/getAllUsers")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ResponseObjectDto> getAllUserList() {
        return userService.getAllUserList();
    }

    @PostMapping("/api/user/changeStatusToOperator")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ResponseObjectDto> setUserStatusToOperatorByUserId(@RequestParam("userId") Integer userId) {
        return userService.setUserStatusToOperatorByUserId(userId);
    }
}
