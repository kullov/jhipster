package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Intern} entity.
 */
public class InternDTO implements Serializable {
    
    private Long id;

    private Integer code;

    private String firstName;

    private String lastName;

    private Instant dateOfBirth;

    private Instant joinDate;

    private String className;

    private String avatar;

    private String password;

    private String email;

    private Integer phone;

    private String description;

    private String address;

    private Set<AbilityDTO> internAbilities = new HashSet<>();

    private Long organizationInternId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Instant joinDate) {
        this.joinDate = joinDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<AbilityDTO> getInternAbilities() {
        return internAbilities;
    }

    public void setInternAbilities(Set<AbilityDTO> abilities) {
        this.internAbilities = abilities;
    }

    public Long getOrganizationInternId() {
        return organizationInternId;
    }

    public void setOrganizationInternId(Long organizationId) {
        this.organizationInternId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InternDTO internDTO = (InternDTO) o;
        if (internDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), internDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InternDTO{" +
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
            ", internAbilities='" + getInternAbilities() + "'" +
            ", organizationInternId=" + getOrganizationInternId() +
            "}";
    }
}
