package ru.requests_registration.work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.requests_registration.auth.dto.UserDto;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private Integer id;
    private String message;
    private LocalDate createDate;
    private LocalDate updateDate;
    private RequestStatusDto requestStatus;
    private UserDto createdBy;
}
