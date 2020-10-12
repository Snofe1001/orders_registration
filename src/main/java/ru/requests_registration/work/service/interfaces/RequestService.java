package ru.requests_registration.work.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.requests_registration.common.dto.ResponseObjectDto;
import ru.requests_registration.work.dto.RequestDto;

@Service
public interface RequestService {

    ResponseEntity<ResponseObjectDto> createRequest(RequestDto requestDto);

    ResponseEntity<ResponseObjectDto> updateRequest(RequestDto requestDto);

    ResponseEntity<ResponseObjectDto> getAllRequestsByUserId(Integer userId);

    ResponseEntity<ResponseObjectDto> getAllPostedRequests();

    ResponseEntity<ResponseObjectDto> sendRequestToOperator(RequestDto requestDto);

    ResponseEntity<ResponseObjectDto> approveRequest(RequestDto requestDto);

    ResponseEntity<ResponseObjectDto> refuseRequest(RequestDto requestDto);
}
