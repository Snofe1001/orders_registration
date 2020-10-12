package ru.requests_registration.auth.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.requests_registration.auth.dto.RoleDto;
import ru.requests_registration.auth.model.Role;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {
    private final ModelMapper mapper;

    @Autowired
    public RoleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<RoleDto> convertToListRoleDto(List<Role> roleList) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roleList) {
            roleDtoList.add(convert(role, RoleDto.class));
        }
        return roleDtoList;
    }

    public <D> D convert(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
