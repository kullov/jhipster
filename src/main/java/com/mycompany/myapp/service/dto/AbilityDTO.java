package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Ability} entity.
 */
public class AbilityDTO implements Serializable {
    
    private Long id;

    private String name;

    private String description;


    private Long typeId;

    private String typeName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long abilityCategoryId) {
        this.typeId = abilityCategoryId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String abilityCategoryName) {
        this.typeName = abilityCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbilityDTO abilityDTO = (AbilityDTO) o;
        if (abilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbilityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", typeId=" + getTypeId() +
            ", typeName='" + getTypeName() + "'" +
            "}";
    }
}
