package ru.requests_registration.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.requests_registration.common.dto.ResponseObjectDto;

public class CommonUtils {

    public static ResponseEntity<ResponseObjectDto> returnDefaultErrorResponseWithMessage(String message) {
        ResponseObjectDto responseObjectDto = new ResponseObjectDto();

        responseObjectDto.setError(true);
        responseObjectDto.setMessage(message);

        return new ResponseEntity<>(responseObjectDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ResponseObjectDto> returnDefaultGoodResponseWithObject(Object object) {
        ResponseObjectDto responseObjectDto = new ResponseObjectDto();

        responseObjectDto.setError(false);
        responseObjectDto.setObject(object);

        return new ResponseEntity<>(responseObjectDto, HttpStatus.OK);
    }
}
