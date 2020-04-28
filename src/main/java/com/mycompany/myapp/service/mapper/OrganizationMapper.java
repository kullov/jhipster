package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OrganizationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {


    @Mapping(target = "requests", ignore = true)
    @Mapping(target = "removeRequest", ignore = true)
    @Mapping(target = "interns", ignore = true)
    @Mapping(target = "removeIntern", ignore = true)
    @Mapping(target = "requestAssignments", ignore = true)
    @Mapping(target = "removeRequestAssignment", ignore = true)
    Organization toEntity(OrganizationDTO organizationDTO);

    default Organization fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
