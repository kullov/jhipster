package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.RequestAssignment} entity.
 */
public class RequestAssignmentDTO implements Serializable {
    
    private Long id;

    private Instant startDate;

    private Instant endDate;

    private ZonedDateTime dateCreated;


    private Long internRequestAssignmentId;

    private String internRequestAssignmentCode;

    private Long organizationRequestAssignmentId;

    private String organizationRequestAssignmentName;

    private Long statusId;

    private String statusName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getInternRequestAssignmentId() {
        return internRequestAssignmentId;
    }

    public void setInternRequestAssignmentId(Long internId) {
        this.internRequestAssignmentId = internId;
    }

    public String getInternRequestAssignmentCode() {
        return internRequestAssignmentCode;
    }

    public void setInternRequestAssignmentCode(String internCode) {
        this.internRequestAssignmentCode = internCode;
    }

    public Long getOrganizationRequestAssignmentId() {
        return organizationRequestAssignmentId;
    }

    public void setOrganizationRequestAssignmentId(Long organizationId) {
        this.organizationRequestAssignmentId = organizationId;
    }

    public String getOrganizationRequestAssignmentName() {
        return organizationRequestAssignmentName;
    }

    public void setOrganizationRequestAssignmentName(String organizationName) {
        this.organizationRequestAssignmentName = organizationName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestAssignmentDTO requestAssignmentDTO = (RequestAssignmentDTO) o;
        if (requestAssignmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestAssignmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestAssignmentDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", internRequestAssignmentId=" + getInternRequestAssignmentId() +
            ", internRequestAssignmentCode='" + getInternRequestAssignmentCode() + "'" +
            ", organizationRequestAssignmentId=" + getOrganizationRequestAssignmentId() +
            ", organizationRequestAssignmentName='" + getOrganizationRequestAssignmentName() + "'" +
            ", statusId=" + getStatusId() +
            ", statusName='" + getStatusName() + "'" +
            "}";
    }
}
