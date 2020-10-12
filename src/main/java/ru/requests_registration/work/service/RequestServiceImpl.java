package ru.requests_registration.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.requests_registration.auth.model.User;
import ru.requests_registration.auth.repository.UserJpaRepository;
import ru.requests_registration.common.CommonUtils;
import ru.requests_registration.common.dto.ResponseObjectDto;
import ru.requests_registration.work.dto.RequestDto;
import ru.requests_registration.work.mapper.RequestMapper;
import ru.requests_registration.work.model.Request;
import ru.requests_registration.work.repository.RequestJpaRepository;
import ru.requests_registration.work.repository.RequestStatusJpaRepository;
import ru.requests_registration.work.service.interfaces.RequestService;

import java.time.LocalDate;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestJpaRepository requestJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final RequestStatusJpaRepository requestStatusJpaRepository;

    private final RequestMapper requestMapper;

    @Autowired
    public RequestServiceImpl(RequestJpaRepository requestJpaRepository, UserJpaRepository userJpaRepository,
                              RequestStatusJpaRepository requestStatusJpaRepository,
                              RequestMapper requestMapper) {
        this.requestJpaRepository = requestJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.requestStatusJpaRepository = requestStatusJpaRepository;

        this.requestMapper = requestMapper;
    }

    @Override
    public ResponseEntity<ResponseObjectDto> createRequest(RequestDto requestDto) {
        try {
            Request request = requestMapper.convert(requestDto, Request.class);
            request.setRequestStatus(requestStatusJpaRepository.getByName("DRAFT"));
            request.setCreateDate(LocalDate.now());
            request.setUpdateDate(LocalDate.now());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userJpaRepository.getByLogin(authentication.getName());
            request.setCreatedBy(currentUser);
            return CommonUtils.returnDefaultGoodResponseWithObject
                    (requestMapper.convert(requestJpaRepository.saveAndFlush(request), RequestDto.class));
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка создания заявки");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> updateRequest(RequestDto requestDto) {
        try {
            if (!requestDto.getRequestStatus().getName().equals("DRAFT")) {
                ResponseObjectDto responseObjectDto = new ResponseObjectDto();
                responseObjectDto.setError(true);
                responseObjectDto.setMessage("Редактировать возможно только заявки в статусе \"Черновик\"");

                return new ResponseEntity<>(responseObjectDto, HttpStatus.NOT_ACCEPTABLE);
            }

            Request updatedRequest = requestJpaRepository.saveAndFlush(requestMapper.convert(requestDto, Request.class));
            return CommonUtils.returnDefaultGoodResponseWithObject(requestMapper.convert(updatedRequest, RequestDto.class));

        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка редактирования заявки");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> getAllRequestsByUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userJpaRepository.getByLogin(authentication.getName());
            return CommonUtils.returnDefaultGoodResponseWithObject
                    (requestMapper.convertToListRequestDto(requestJpaRepository.getByCreatedById(currentUser.getId())));
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка получения заявок по пользователю");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> getAllPostedRequests() {
        try {
            return CommonUtils.returnDefaultGoodResponseWithObject(requestMapper.convertToListRequestDto(requestJpaRepository.getAllPostedRequests()));
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка получения отправленных заявок");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> sendRequestToOperator(RequestDto requestDto) {
        try {
            if (requestDto.getRequestStatus().getName().equals("DRAFT")) {
                requestJpaRepository.postRequestById(requestDto.getId());
                return CommonUtils.returnDefaultGoodResponseWithObject
                        (requestMapper.convert(requestJpaRepository.getById(requestDto.getId()), RequestDto.class));
            }
            ResponseObjectDto responseObjectDto = new ResponseObjectDto();
            responseObjectDto.setError(true);
            responseObjectDto.setMessage("Только заявки в статусе \"Черновик\" можно отправлять");

            return new ResponseEntity<>(responseObjectDto, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка отправки заявки");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> approveRequest(RequestDto requestDto) {
        try {
            if (requestDto.getRequestStatus().getName().equals("POSTED")) {
                requestJpaRepository.approveRequestById(requestDto.getId());
                return CommonUtils.returnDefaultGoodResponseWithObject
                        (requestMapper.convert(requestJpaRepository.getById(requestDto.getId()), RequestDto.class));
            }
            ResponseObjectDto responseObjectDto = new ResponseObjectDto();
            responseObjectDto.setError(true);
            responseObjectDto.setMessage("Только заявки в статусе \"Отправлено\" можно подтверждать");

            return new ResponseEntity<>(responseObjectDto, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка подтверждения заявки");
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDto> refuseRequest(RequestDto requestDto) {
        try {
            if (requestDto.getRequestStatus().getName().equals("POSTED")) {
                requestJpaRepository.refuseRequestById(requestDto.getId());
                return CommonUtils.returnDefaultGoodResponseWithObject
                        (requestMapper.convert(requestJpaRepository.getById(requestDto.getId()), RequestDto.class));
            }
            ResponseObjectDto responseObjectDto = new ResponseObjectDto();
            responseObjectDto.setError(true);
            responseObjectDto.setMessage("Только заявки в статусе \"Отправлено\" можно отменить");

            return new ResponseEntity<>(responseObjectDto, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return CommonUtils.returnDefaultErrorResponseWithMessage("Ошибка отмены заявки");
        }
    }
}
