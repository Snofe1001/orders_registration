package ru.requests_registration.work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStatusDto {

    private Integer id;
    private String name;
    private String description;
}
