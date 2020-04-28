package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RequestAssignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestAssignment} and its DTO {@link RequestAssignmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {InternMapper.class, OrganizationMapper.class, StatusMapper.class})
public interface RequestAssignmentMapper extends EntityMapper<RequestAssignmentDTO, RequestAssignment> {

    @Mapping(source = "internRequestAssignment.id", target = "internRequestAssignmentId")
    @Mapping(source = "internRequestAssignment.code", target = "internRequestAssignmentCode")
    @Mapping(source = "organizationRequestAssignment.id", target = "organizationRequestAssignmentId")
    @Mapping(source = "organizationRequestAssignment.name", target = "organizationRequestAssignmentName")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "status.name", target = "statusName")
    RequestAssignmentDTO toDto(RequestAssignment requestAssignment);

    @Mapping(source = "internRequestAssignmentId", target = "internRequestAssignment")
    @Mapping(source = "organizationRequestAssignmentId", target = "organizationRequestAssignment")
    @Mapping(source = "statusId", target = "status")
    RequestAssignment toEntity(RequestAssignmentDTO requestAssignmentDTO);

    default RequestAssignment fromId(Long id) {
        if (id == null) {
            return null;
        }
        RequestAssignment requestAssignment = new RequestAssignment();
        requestAssignment.setId(id);
        return requestAssignment;
    }
}
