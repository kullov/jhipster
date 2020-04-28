package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.RegisterRequest;
import com.mycompany.myapp.repository.RegisterRequestRepository;
import com.mycompany.myapp.service.RegisterRequestService;
import com.mycompany.myapp.service.dto.RegisterRequestDTO;
import com.mycompany.myapp.service.mapper.RegisterRequestMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RegisterRequestResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RegisterRequestResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Autowired
    private RegisterRequestMapper registerRequestMapper;

    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegisterRequestMockMvc;

    private RegisterRequest registerRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterRequest createEntity(EntityManager em) {
        RegisterRequest registerRequest = new RegisterRequest()
            .dateCreated(DEFAULT_DATE_CREATED)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return registerRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RegisterRequest createUpdatedEntity(EntityManager em) {
        RegisterRequest registerRequest = new RegisterRequest()
            .dateCreated(UPDATED_DATE_CREATED)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return registerRequest;
    }

    @BeforeEach
    public void initTest() {
        registerRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegisterRequest() throws Exception {
        int databaseSizeBeforeCreate = registerRequestRepository.findAll().size();

        // Create the RegisterRequest
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);
        restRegisterRequestMockMvc.perform(post("/api/register-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeCreate + 1);
        RegisterRequest testRegisterRequest = registerRequestList.get(registerRequestList.size() - 1);
        assertThat(testRegisterRequest.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testRegisterRequest.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testRegisterRequest.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createRegisterRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registerRequestRepository.findAll().size();

        // Create the RegisterRequest with an existing ID
        registerRequest.setId(1L);
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegisterRequestMockMvc.perform(post("/api/register-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRegisterRequests() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        // Get all the registerRequestList
        restRegisterRequestMockMvc.perform(get("/api/register-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registerRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        // Get the registerRequest
        restRegisterRequestMockMvc.perform(get("/api/register-requests/{id}", registerRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(registerRequest.getId().intValue()))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRegisterRequest() throws Exception {
        // Get the registerRequest
        restRegisterRequestMockMvc.perform(get("/api/register-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        int databaseSizeBeforeUpdate = registerRequestRepository.findAll().size();

        // Update the registerRequest
        RegisterRequest updatedRegisterRequest = registerRequestRepository.findById(registerRequest.getId()).get();
        // Disconnect from session so that the updates on updatedRegisterRequest are not directly saved in db
        em.detach(updatedRegisterRequest);
        updatedRegisterRequest
            .dateCreated(UPDATED_DATE_CREATED)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(updatedRegisterRequest);

        restRegisterRequestMockMvc.perform(put("/api/register-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isOk());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeUpdate);
        RegisterRequest testRegisterRequest = registerRequestList.get(registerRequestList.size() - 1);
        assertThat(testRegisterRequest.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testRegisterRequest.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testRegisterRequest.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingRegisterRequest() throws Exception {
        int databaseSizeBeforeUpdate = registerRequestRepository.findAll().size();

        // Create the RegisterRequest
        RegisterRequestDTO registerRequestDTO = registerRequestMapper.toDto(registerRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegisterRequestMockMvc.perform(put("/api/register-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(registerRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisterRequest in the database
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegisterRequest() throws Exception {
        // Initialize the database
        registerRequestRepository.saveAndFlush(registerRequest);

        int databaseSizeBeforeDelete = registerRequestRepository.findAll().size();

        // Delete the registerRequest
        restRegisterRequestMockMvc.perform(delete("/api/register-requests/{id}", registerRequest.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RegisterRequest> registerRequestList = registerRequestRepository.findAll();
        assertThat(registerRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
