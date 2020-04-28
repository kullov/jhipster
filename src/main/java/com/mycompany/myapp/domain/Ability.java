package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ability.
 */
@Entity
@Table(name = "ability")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("abilityTypes")
    private AbilityCategory type;

    @ManyToMany(mappedBy = "internAbilities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Intern> interns = new HashSet<>();

    @ManyToMany(mappedBy = "requestAbilities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Request> requests = new HashSet<>();

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

    public Ability name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Ability description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbilityCategory getType() {
        return type;
    }

    public Ability type(AbilityCategory abilityCategory) {
        this.type = abilityCategory;
        return this;
    }

    public void setType(AbilityCategory abilityCategory) {
        this.type = abilityCategory;
    }

    public Set<Intern> getInterns() {
        return interns;
    }

    public Ability interns(Set<Intern> interns) {
        this.interns = interns;
        return this;
    }

    public Ability addIntern(Intern intern) {
        this.interns.add(intern);
        intern.getInternAbilities().add(this);
        return this;
    }

    public Ability removeIntern(Intern intern) {
        this.interns.remove(intern);
        intern.getInternAbilities().remove(this);
        return this;
    }

    public void setInterns(Set<Intern> interns) {
        this.interns = interns;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Ability requests(Set<Request> requests) {
        this.requests = requests;
        return this;
    }

    public Ability addRequest(Request request) {
        this.requests.add(request);
        request.getRequestAbilities().add(this);
        return this;
    }

    public Ability removeRequest(Request request) {
        this.requests.remove(request);
        request.getRequestAbilities().remove(this);
        return this;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ability)) {
            return false;
        }
        return id != null && id.equals(((Ability) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ability{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
