package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Request.
 */
@Entity
@Table(name = "request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    private String position;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "requestRegister")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegisterRequest> registerRequests = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_request_ability",
               joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "request_ability_id", referencedColumnName = "id"))
    private Set<Ability> requestAbilities = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("requests")
    private Organization organizationRequest;

    @ManyToOne
    @JsonIgnoreProperties("requests")
    private Status requestStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public Request position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAmount() {
        return amount;
    }

    public Request amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public Request dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getStatus() {
        return status;
    }

    public Request status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public Request description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Request type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<RegisterRequest> getRegisterRequests() {
        return registerRequests;
    }

    public Request registerRequests(Set<RegisterRequest> registerRequests) {
        this.registerRequests = registerRequests;
        return this;
    }

    public Request addRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequests.add(registerRequest);
        registerRequest.setRequestRegister(this);
        return this;
    }

    public Request removeRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequests.remove(registerRequest);
        registerRequest.setRequestRegister(null);
        return this;
    }

    public void setRegisterRequests(Set<RegisterRequest> registerRequests) {
        this.registerRequests = registerRequests;
    }

    public Set<Ability> getRequestAbilities() {
        return requestAbilities;
    }

    public Request requestAbilities(Set<Ability> abilities) {
        this.requestAbilities = abilities;
        return this;
    }

    public Request addRequestAbility(Ability ability) {
        this.requestAbilities.add(ability);
        ability.getRequests().add(this);
        return this;
    }

    public Request removeRequestAbility(Ability ability) {
        this.requestAbilities.remove(ability);
        ability.getRequests().remove(this);
        return this;
    }

    public void setRequestAbilities(Set<Ability> abilities) {
        this.requestAbilities = abilities;
    }

    public Organization getOrganizationRequest() {
        return organizationRequest;
    }

    public Request organizationRequest(Organization organization) {
        this.organizationRequest = organization;
        return this;
    }

    public void setOrganizationRequest(Organization organization) {
        this.organizationRequest = organization;
    }

    public Status getRequestStatus() {
        return requestStatus;
    }

    public Request requestStatus(Status status) {
        this.requestStatus = status;
        return this;
    }

    public void setRequestStatus(Status status) {
        this.requestStatus = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Request{" +
            "id=" + getId() +
            ", position='" + getPosition() + "'" +
            ", amount=" + getAmount() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
