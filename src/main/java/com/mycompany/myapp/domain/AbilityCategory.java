package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A AbilityCategory.
 */
@Entity
@Table(name = "ability_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbilityCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ability> abilityTypes = new HashSet<>();

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

    public AbilityCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ability> getAbilityTypes() {
        return abilityTypes;
    }

    public AbilityCategory abilityTypes(Set<Ability> abilities) {
        this.abilityTypes = abilities;
        return this;
    }

    public AbilityCategory addAbilityType(Ability ability) {
        this.abilityTypes.add(ability);
        ability.setType(this);
        return this;
    }

    public AbilityCategory removeAbilityType(Ability ability) {
        this.abilityTypes.remove(ability);
        ability.setType(null);
        return this;
    }

    public void setAbilityTypes(Set<Ability> abilities) {
        this.abilityTypes = abilities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbilityCategory)) {
            return false;
        }
        return id != null && id.equals(((AbilityCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AbilityCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
