package ru.requests_registration.work.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.requests_registration.common.dto.ResponseObjectDto;
import ru.requests_registration.work.dto.RequestDto;
import ru.requests_registration.work.service.interfaces.RequestService;

@RestController
@Transactional
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/api/request/createRequest")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<ResponseObjectDto> createRequest(@RequestBody RequestDto requestDto) {
        return requestService.createRequest(requestDto);
    }

    @PostMapping("/api/request/updateRequest")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<ResponseObjectDto> updateRequest(@RequestBody RequestDto requestDto) {
        return requestService.updateRequest(requestDto);
    }

    @GetMapping("/api/request/getAllRequestsByUserId")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<ResponseObjectDto> getAllRequestsByUserId() {
        return requestService.getAllRequestsByUserId();
    }

    @GetMapping("/api/request/getAllPostedRequests")
    @ResponseBody
    @Secured("ROLE_OPERATOR")
    public ResponseEntity<ResponseObjectDto> getAllPostedRequests() {
        return requestService.getAllPostedRequests();
    }

    @PostMapping("/api/request/sendRequestToOperator")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<ResponseObjectDto> sendRequestToOperator(@RequestBody RequestDto requestDto) {
        return requestService.sendRequestToOperator(requestDto);
    }

    @PostMapping("/api/request/approveRequest")
    @ResponseBody
    @Secured("ROLE_OPERATOR")
    public ResponseEntity<ResponseObjectDto> approveRequest(@RequestBody RequestDto requestDto) {
        return requestService.approveRequest(requestDto);
    }

    @PostMapping("/api/request/refuseRequest")
    @ResponseBody
    @Secured("ROLE_OPERATOR")
    public ResponseEntity<ResponseObjectDto> refuseRequest(@RequestBody RequestDto requestDto) {
        return requestService.refuseRequest(requestDto);
    }
}
