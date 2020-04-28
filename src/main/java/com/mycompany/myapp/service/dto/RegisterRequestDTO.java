package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.RegisterRequest} entity.
 */
public class RegisterRequestDTO implements Serializable {
    
    private Long id;

    private ZonedDateTime dateCreated;

    private Instant startDate;

    private Instant endDate;


    private Long internRegisterId;

    private String internRegisterCode;

    private Long requestRegisterId;

    private String requestRegisterPosition;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getInternRegisterId() {
        return internRegisterId;
    }

    public void setInternRegisterId(Long internId) {
        this.internRegisterId = internId;
    }

    public String getInternRegisterCode() {
        return internRegisterCode;
    }

    public void setInternRegisterCode(String internCode) {
        this.internRegisterCode = internCode;
    }

    public Long getRequestRegisterId() {
        return requestRegisterId;
    }

    public void setRequestRegisterId(Long requestId) {
        this.requestRegisterId = requestId;
    }

    public String getRequestRegisterPosition() {
        return requestRegisterPosition;
    }

    public void setRequestRegisterPosition(String requestPosition) {
        this.requestRegisterPosition = requestPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO) o;
        if (registerRequestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registerRequestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
            "id=" + getId() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", internRegisterId=" + getInternRegisterId() +
            ", internRegisterCode='" + getInternRegisterCode() + "'" +
            ", requestRegisterId=" + getRequestRegisterId() +
            ", requestRegisterPosition='" + getRequestRegisterPosition() + "'" +
            "}";
    }
}
