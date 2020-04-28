package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Status.
 */
@Entity
@Table(name = "status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "requestStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Request> requests = new HashSet<>();

    @OneToMany(mappedBy = "status")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestAssignment> requestAssignments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Status name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Status requests(Set<Request> requests) {
        this.requests = requests;
        return this;
    }

    public Status addRequest(Request request) {
        this.requests.add(request);
        request.setRequestStatus(this);
        return this;
    }

    public Status removeRequest(Request request) {
        this.requests.remove(request);
        request.setRequestStatus(null);
        return this;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Set<RequestAssignment> getRequestAssignments() {
        return requestAssignments;
    }

    public Status requestAssignments(Set<RequestAssignment> requestAssignments) {
        this.requestAssignments = requestAssignments;
        return this;
    }

    public Status addRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.add(requestAssignment);
        requestAssignment.setStatus(this);
        return this;
    }

    public Status removeRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.remove(requestAssignment);
        requestAssignment.setStatus(null);
        return this;
    }

    public void setRequestAssignments(Set<RequestAssignment> requestAssignments) {
        this.requestAssignments = requestAssignments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Status)) {
            return false;
        }
        return id != null && id.equals(((Status) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Status{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
