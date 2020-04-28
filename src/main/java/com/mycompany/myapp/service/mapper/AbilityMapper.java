package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AbilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ability} and its DTO {@link AbilityDTO}.
 */
@Mapper(componentModel = "spring", uses = {AbilityCategoryMapper.class})
public interface AbilityMapper extends EntityMapper<AbilityDTO, Ability> {

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    AbilityDTO toDto(Ability ability);

    @Mapping(source = "typeId", target = "type")
    @Mapping(target = "interns", ignore = true)
    @Mapping(target = "removeIntern", ignore = true)
    @Mapping(target = "requests", ignore = true)
    @Mapping(target = "removeRequest", ignore = true)
    Ability toEntity(AbilityDTO abilityDTO);

    default Ability fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ability ability = new Ability();
        ability.setId(id);
        return ability;
    }
}
