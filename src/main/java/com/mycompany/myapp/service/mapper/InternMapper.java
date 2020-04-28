package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InternDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Intern} and its DTO {@link InternDTO}.
 */
@Mapper(componentModel = "spring", uses = {AbilityMapper.class, OrganizationMapper.class})
public interface InternMapper extends EntityMapper<InternDTO, Intern> {

    @Mapping(source = "organizationIntern.id", target = "organizationInternId")
    InternDTO toDto(Intern intern);

    @Mapping(target = "registerRequests", ignore = true)
    @Mapping(target = "removeRegisterRequest", ignore = true)
    @Mapping(target = "requestAssignments", ignore = true)
    @Mapping(target = "removeRequestAssignment", ignore = true)
    @Mapping(target = "removeInternAbility", ignore = true)
    @Mapping(source = "organizationInternId", target = "organizationIntern")
    Intern toEntity(InternDTO internDTO);

    default Intern fromId(Long id) {
        if (id == null) {
            return null;
        }
        Intern intern = new Intern();
        intern.setId(id);
        return intern;
    }
}
