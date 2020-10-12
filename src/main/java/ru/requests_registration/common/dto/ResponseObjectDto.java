package ru.requests_registration.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObjectDto {

    private boolean isError;
    private String message;
    private Object object;
}
