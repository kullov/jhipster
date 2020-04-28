package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * A RequestAssignment.
 */
@Entity
@Table(name = "request_assignment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @ManyToOne
    @JsonIgnoreProperties("requestAssignments")
    private Intern internRequestAssignment;

    @ManyToOne
    @JsonIgnoreProperties("requestAssignments")
    private Organization organizationRequestAssignment;

    @ManyToOne
    @JsonIgnoreProperties("requestAssignments")
    private Status status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public RequestAssignment startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public RequestAssignment endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public RequestAssignment dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Intern getInternRequestAssignment() {
        return internRequestAssignment;
    }

    public RequestAssignment internRequestAssignment(Intern intern) {
        this.internRequestAssignment = intern;
        return this;
    }

    public void setInternRequestAssignment(Intern intern) {
        this.internRequestAssignment = intern;
    }

    public Organization getOrganizationRequestAssignment() {
        return organizationRequestAssignment;
    }

    public RequestAssignment organizationRequestAssignment(Organization organization) {
        this.organizationRequestAssignment = organization;
        return this;
    }

    public void setOrganizationRequestAssignment(Organization organization) {
        this.organizationRequestAssignment = organization;
    }

    public Status getStatus() {
        return status;
    }

    public RequestAssignment status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestAssignment)) {
            return false;
        }
        return id != null && id.equals(((RequestAssignment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RequestAssignment{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            "}";
    }
}
