package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_count")
    private Integer employeeCount;

    @Column(name = "gross_revenue")
    private String grossRevenue;

    @Column(name = "name")
    private String name;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "organizationRequest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Request> requests = new HashSet<>();

    @OneToMany(mappedBy = "organizationIntern")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Intern> interns = new HashSet<>();

    @OneToMany(mappedBy = "organizationRequestAssignment")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestAssignment> requestAssignments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public Organization employeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getGrossRevenue() {
        return grossRevenue;
    }

    public Organization grossRevenue(String grossRevenue) {
        this.grossRevenue = grossRevenue;
        return this;
    }

    public void setGrossRevenue(String grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public String getName() {
        return name;
    }

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public Organization taxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
        return this;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPassword() {
        return password;
    }

    public Organization password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Organization email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public Organization contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public Organization description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public Organization address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Organization requests(Set<Request> requests) {
        this.requests = requests;
        return this;
    }

    public Organization addRequest(Request request) {
        this.requests.add(request);
        request.setOrganizationRequest(this);
        return this;
    }

    public Organization removeRequest(Request request) {
        this.requests.remove(request);
        request.setOrganizationRequest(null);
        return this;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Set<Intern> getInterns() {
        return interns;
    }

    public Organization interns(Set<Intern> interns) {
        this.interns = interns;
        return this;
    }

    public Organization addIntern(Intern intern) {
        this.interns.add(intern);
        intern.setOrganizationIntern(this);
        return this;
    }

    public Organization removeIntern(Intern intern) {
        this.interns.remove(intern);
        intern.setOrganizationIntern(null);
        return this;
    }

    public void setInterns(Set<Intern> interns) {
        this.interns = interns;
    }

    public Set<RequestAssignment> getRequestAssignments() {
        return requestAssignments;
    }

    public Organization requestAssignments(Set<RequestAssignment> requestAssignments) {
        this.requestAssignments = requestAssignments;
        return this;
    }

    public Organization addRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.add(requestAssignment);
        requestAssignment.setOrganizationRequestAssignment(this);
        return this;
    }

    public Organization removeRequestAssignment(RequestAssignment requestAssignment) {
        this.requestAssignments.remove(requestAssignment);
        requestAssignment.setOrganizationRequestAssignment(null);
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
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organization{" +
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
