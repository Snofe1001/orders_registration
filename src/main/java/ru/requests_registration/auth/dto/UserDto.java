package ru.requests_registration.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String login;
    private String password;
    private Boolean isEnable;
    private Set<RoleDto> roleSet;
}
