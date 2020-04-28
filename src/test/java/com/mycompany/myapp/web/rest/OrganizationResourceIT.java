package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.Organization;
import com.mycompany.myapp.repository.OrganizationRepository;
import com.mycompany.myapp.service.OrganizationService;
import com.mycompany.myapp.service.dto.OrganizationDTO;
import com.mycompany.myapp.service.mapper.OrganizationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrganizationResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrganizationResourceIT {

    private static final Integer DEFAULT_EMPLOYEE_COUNT = 1;
    private static final Integer UPDATED_EMPLOYEE_COUNT = 2;

    private static final String DEFAULT_GROSS_REVENUE = "AAAAAAAAAA";
    private static final String UPDATED_GROSS_REVENUE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganizationMockMvc;

    private Organization organization;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createEntity(EntityManager em) {
        Organization organization = new Organization()
            .employeeCount(DEFAULT_EMPLOYEE_COUNT)
            .grossRevenue(DEFAULT_GROSS_REVENUE)
            .name(DEFAULT_NAME)
            .taxNumber(DEFAULT_TAX_NUMBER)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .contact(DEFAULT_CONTACT)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS);
        return organization;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createUpdatedEntity(EntityManager em) {
        Organization organization = new Organization()
            .employeeCount(UPDATED_EMPLOYEE_COUNT)
            .grossRevenue(UPDATED_GROSS_REVENUE)
            .name(UPDATED_NAME)
            .taxNumber(UPDATED_TAX_NUMBER)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .contact(UPDATED_CONTACT)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS);
        return organization;
    }

    @BeforeEach
    public void initTest() {
        organization = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganization() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);
        restOrganizationMockMvc.perform(post("/api/organizations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationDTO)))
            .andExpect(status().isCreated());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate + 1);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getEmployeeCount()).isEqualTo(DEFAULT_EMPLOYEE_COUNT);
        assertThat(testOrganization.getGrossRevenue()).isEqualTo(DEFAULT_GROSS_REVENUE);
        assertThat(testOrganization.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganization.getTaxNumber()).isEqualTo(DEFAULT_TAX_NUMBER);
        assertThat(testOrganization.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testOrganization.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrganization.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testOrganization.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrganization.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createOrganizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().size();

        // Create the Organization with an existing ID
        organization.setId(1L);
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizationMockMvc.perform(post("/api/organizations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrganizations() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get all the organizationList
        restOrganizationMockMvc.perform(get("/api/organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organization.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeCount").value(hasItem(DEFAULT_EMPLOYEE_COUNT)))
            .andExpect(jsonPath("$.[*].grossRevenue").value(hasItem(DEFAULT_GROSS_REVENUE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].taxNumber").value(hasItem(DEFAULT_TAX_NUMBER)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }
    
    @Test
    @Transactional
    public void getOrganization() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", organization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organization.getId().intValue()))
            .andExpect(jsonPath("$.employeeCount").value(DEFAULT_EMPLOYEE_COUNT))
            .andExpect(jsonPath("$.grossRevenue").value(DEFAULT_GROSS_REVENUE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.taxNumber").value(DEFAULT_TAX_NUMBER))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    public void getNonExistingOrganization() throws Exception {
        // Get the organization
        restOrganizationMockMvc.perform(get("/api/organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganization() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Update the organization
        Organization updatedOrganization = organizationRepository.findById(organization.getId()).get();
        // Disconnect from session so that the updates on updatedOrganization are not directly saved in db
        em.detach(updatedOrganization);
        updatedOrganization
            .employeeCount(UPDATED_EMPLOYEE_COUNT)
            .grossRevenue(UPDATED_GROSS_REVENUE)
            .name(UPDATED_NAME)
            .taxNumber(UPDATED_TAX_NUMBER)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .contact(UPDATED_CONTACT)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS);
        OrganizationDTO organizationDTO = organizationMapper.toDto(updatedOrganization);

        restOrganizationMockMvc.perform(put("/api/organizations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationDTO)))
            .andExpect(status().isOk());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getEmployeeCount()).isEqualTo(UPDATED_EMPLOYEE_COUNT);
        assertThat(testOrganization.getGrossRevenue()).isEqualTo(UPDATED_GROSS_REVENUE);
        assertThat(testOrganization.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganization.getTaxNumber()).isEqualTo(UPDATED_TAX_NUMBER);
        assertThat(testOrganization.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testOrganization.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrganization.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testOrganization.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrganization.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().size();

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizationMockMvc.perform(put("/api/organizations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganization() throws Exception {
        // Initialize the database
        organizationRepository.saveAndFlush(organization);

        int databaseSizeBeforeDelete = organizationRepository.findAll().size();

        // Delete the organization
        restOrganizationMockMvc.perform(delete("/api/organizations/{id}", organization.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organization> organizationList = organizationRepository.findAll();
        assertThat(organizationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
