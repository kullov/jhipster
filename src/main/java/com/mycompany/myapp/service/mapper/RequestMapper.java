package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {AbilityMapper.class, OrganizationMapper.class, StatusMapper.class})
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {

    @Mapping(source = "organizationRequest.id", target = "organizationRequestId")
    @Mapping(source = "organizationRequest.name", target = "organizationRequestName")
    @Mapping(source = "requestStatus.id", target = "requestStatusId")
    @Mapping(source = "requestStatus.name", target = "requestStatusName")
    RequestDTO toDto(Request request);

    @Mapping(target = "registerRequests", ignore = true)
    @Mapping(target = "removeRegisterRequest", ignore = true)
    @Mapping(target = "removeRequestAbility", ignore = true)
    @Mapping(source = "organizationRequestId", target = "organizationRequest")
    @Mapping(source = "requestStatusId", target = "requestStatus")
    Request toEntity(RequestDTO requestDTO);

    default Request fromId(Long id) {
        if (id == null) {
            return null;
        }
        Request request = new Request();
        request.setId(id);
        return request;
    }
}
