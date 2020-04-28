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
 * A RegisterRequest.
 */
@Entity
@Table(name = "register_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @ManyToOne
    @JsonIgnoreProperties("registerRequests")
    private Intern internRegister;

    @ManyToOne
    @JsonIgnoreProperties("registerRequests")
    private Request requestRegister;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public RegisterRequest dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public RegisterRequest startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public RegisterRequest endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Intern getInternRegister() {
        return internRegister;
    }

    public RegisterRequest internRegister(Intern intern) {
        this.internRegister = intern;
        return this;
    }

    public void setInternRegister(Intern intern) {
        this.internRegister = intern;
    }

    public Request getRequestRegister() {
        return requestRegister;
    }

    public RegisterRequest requestRegister(Request request) {
        this.requestRegister = request;
        return this;
    }

    public void setRequestRegister(Request request) {
        this.requestRegister = request;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterRequest)) {
            return false;
        }
        return id != null && id.equals(((RegisterRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
            "id=" + getId() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
