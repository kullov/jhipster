package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {
    
    private Long id;

    private Integer employeeCount;

    private String grossRevenue;

    private String name;

    private String taxNumber;

    private String password;

    private String email;

    private String contact;

    private String description;

    private String address;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(String grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationDTO organizationDTO = (OrganizationDTO) o;
        if (organizationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", employeeCount=" + getEmployeeCount() +
            ", grossRevenue='" + getGrossRevenue() + "'" +
            ", name='" + getName() + "'" +
            ", taxNumber='" + getTaxNumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", contact='" + getContact() + "'" +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
