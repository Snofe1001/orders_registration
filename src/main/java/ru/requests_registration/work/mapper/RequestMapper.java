package ru.requests_registration.work.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.requests_registration.work.dto.RequestDto;
import ru.requests_registration.work.model.Request;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestMapper {
    private final ModelMapper mapper;

    @Autowired
    public RequestMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<RequestDto> convertToListRequestDto(List<Request> requestList) {
        List<RequestDto> requestDtoList = new ArrayList<>();
        for (Request request : requestList) {
            requestDtoList.add(convert(request, RequestDto.class));
        }
        return requestDtoList;
    }

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
