package com.mycompany.myapp.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Request} entity.
 */
public class RequestDTO implements Serializable {
    
    private Long id;

    private String position;

    private Integer amount;

    private ZonedDateTime dateCreated;

    private Integer status;

    private String description;

    private String type;

    private Set<AbilityDTO> requestAbilities = new HashSet<>();

    private Long organizationRequestId;

    private String organizationRequestName;

    private Long requestStatusId;

    private String requestStatusName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<AbilityDTO> getRequestAbilities() {
        return requestAbilities;
    }

    public void setRequestAbilities(Set<AbilityDTO> abilities) {
        this.requestAbilities = abilities;
    }

    public Long getOrganizationRequestId() {
        return organizationRequestId;
    }

    public void setOrganizationRequestId(Long organizationId) {
        this.organizationRequestId = organizationId;
    }

    public String getOrganizationRequestName() {
        return organizationRequestName;
    }

    public void setOrganizationRequestName(String organizationName) {
        this.organizationRequestName = organizationName;
    }

    public Long getRequestStatusId() {
        return requestStatusId;
    }

    public void setRequestStatusId(Long statusId) {
        this.requestStatusId = statusId;
    }

    public String getRequestStatusName() {
        return requestStatusName;
    }

    public void setRequestStatusName(String statusName) {
        this.requestStatusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestDTO requestDTO = (RequestDTO) o;
        if (requestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
            "id=" + getId() +
            ", position='" + getPosition() + "'" +
            ", amount=" + getAmount() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", requestAbilities='" + getRequestAbilities() + "'" +
            ", organizationRequestId=" + getOrganizationRequestId() +
            ", organizationRequestName='" + getOrganizationRequestName() + "'" +
            ", requestStatusId=" + getRequestStatusId() +
            ", requestStatusName='" + getRequestStatusName() + "'" +
            "}";
    }
}
