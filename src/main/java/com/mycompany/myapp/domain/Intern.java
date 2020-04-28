package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Intern.
 */
@Entity
@Table(name = "intern")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Intern implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "join_date")
    private Instant joinDate;

    @Column(name = "class_name")
    private String className;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "internRegister")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegisterRequest> registerRequests = new HashSet<>();

    @OneToMany(mappedBy = "internRequestAssignment")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestAssignment> requestAssignments = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "intern_intern_ability",
               joinColumns = @JoinColumn(name = "intern_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "intern_ability_id", referencedColumnName = "id"))
    private Set<Ability> internAbilities = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("interns")
    private Organization organizationIntern;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public Intern code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public Intern firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Intern lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public Intern dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getJoinDate() {
        return joinDate;
    }

    public Intern joinDate(Instant joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public void setJoinDate(Instant joinDate) {
        this.joinDate = joinDate;
    }

    public String getClassName() {
        return className;
    }

    public Intern className(String className) {
        this.className = className;
        return this;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAvatar() {
        return avatar;
    }

    public Intern avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public Intern password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Intern email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public Intern phone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public Intern description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public Intern address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<RegisterRequest> getRegisterRequests() {
        return registerRequests;
    }

    public Intern registerRequests(Set<RegisterRequest> registerRequests) {
        this.registerRequests = registerRequests;
        return this;
    }

    public Intern addRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequests.add(registerRequest);
        registerRequest.setInternRegister(this);
        return this;
    }

    public Intern removeRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequests.remove(registerRequest);
        registerRequest.setInternRegister(null);
        return this;
    }

    public void setRegisterRequests(Set<RegisterRequest> registerRequests) {
        this.registerRequests = registerRequests;
    }

    public Set<RequestAssignment> getRequestAssignments() {
        return requestAssignments;
    }

    public Intern requestAssignments(Set<RequestAssignment> requestAssignments) {
        this.requestAssignments = requestAssignments;
        return this;
    }

    public Intern addRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.add(requestAssignment);
        requestAssignment.setInternRequestAssignment(this);
        return this;
    }

    public Intern removeRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.remove(requestAssignment);
        requestAssignment.setInternRequestAssignment(null);
        return this;
    }

    public void setRequestAssignments(Set<RequestAssignment> requestAssignments) {
        this.requestAssignments = requestAssignments;
    }

    public Set<Ability> getInternAbilities() {
        return internAbilities;
    }

    public Intern internAbilities(Set<Ability> abilities) {
        this.internAbilities = abilities;
        return this;
    }

    public Intern addInternAbility(Ability ability) {
        this.internAbilities.add(ability);
        ability.getInterns().add(this);
        return this;
    }

    public Intern removeInternAbility(Ability ability) {
        this.internAbilities.remove(ability);
        ability.getInterns().remove(this);
        return this;
    }

    public void setInternAbilities(Set<Ability> abilities) {
        this.internAbilities = abilities;
    }

    public Organization getOrganizationIntern() {
        return organizationIntern;
    }

    public Intern organizationIntern(Organization organization) {
        this.organizationIntern = organization;
        return this;
    }

    public void setOrganizationIntern(Organization organization) {
        this.organizationIntern = organization;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Intern)) {
            return false;
        }
        return id != null && id.equals(((Intern) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Intern{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", className='" + getClassName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
