package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RegisterRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RegisterRequest} and its DTO {@link RegisterRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {InternMapper.class, RequestMapper.class})
public interface RegisterRequestMapper extends EntityMapper<RegisterRequestDTO, RegisterRequest> {

    @Mapping(source = "internRegister.id", target = "internRegisterId")
    @Mapping(source = "internRegister.code", target = "internRegisterCode")
    @Mapping(source = "requestRegister.id", target = "requestRegisterId")
    @Mapping(source = "requestRegister.position", target = "requestRegisterPosition")
    RegisterRequestDTO toDto(RegisterRequest registerRequest);

    @Mapping(source = "internRegisterId", target = "internRegister")
    @Mapping(source = "requestRegisterId", target = "requestRegister")
    RegisterRequest toEntity(RegisterRequestDTO registerRequestDTO);

    default RegisterRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setId(id);
        return registerRequest;
    }
}
