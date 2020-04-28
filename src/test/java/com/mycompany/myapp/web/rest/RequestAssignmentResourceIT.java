package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.RequestAssignment;
import com.mycompany.myapp.repository.RequestAssignmentRepository;
import com.mycompany.myapp.service.RequestAssignmentService;
import com.mycompany.myapp.service.dto.RequestAssignmentDTO;
import com.mycompany.myapp.service.mapper.RequestAssignmentMapper;

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
 * Integration tests for the {@link RequestAssignmentResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RequestAssignmentResourceIT {

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private RequestAssignmentRepository requestAssignmentRepository;

    @Autowired
    private RequestAssignmentMapper requestAssignmentMapper;

    @Autowired
    private RequestAssignmentService requestAssignmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestAssignmentMockMvc;

    private RequestAssignment requestAssignment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestAssignment createEntity(EntityManager em) {
        RequestAssignment requestAssignment = new RequestAssignment()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .dateCreated(DEFAULT_DATE_CREATED);
        return requestAssignment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestAssignment createUpdatedEntity(EntityManager em) {
        RequestAssignment requestAssignment = new RequestAssignment()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .dateCreated(UPDATED_DATE_CREATED);
        return requestAssignment;
    }

    @BeforeEach
    public void initTest() {
        requestAssignment = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestAssignment() throws Exception {
        int databaseSizeBeforeCreate = requestAssignmentRepository.findAll().size();

        // Create the RequestAssignment
        RequestAssignmentDTO requestAssignmentDTO = requestAssignmentMapper.toDto(requestAssignment);
        restRequestAssignmentMockMvc.perform(post("/api/request-assignments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestAssignmentDTO)))
            .andExpect(status().isCreated());

        // Validate the RequestAssignment in the database
        List<RequestAssignment> requestAssignmentList = requestAssignmentRepository.findAll();
        assertThat(requestAssignmentList).hasSize(databaseSizeBeforeCreate + 1);
        RequestAssignment testRequestAssignment = requestAssignmentList.get(requestAssignmentList.size() - 1);
        assertThat(testRequestAssignment.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testRequestAssignment.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testRequestAssignment.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
    }

    @Test
    @Transactional
    public void createRequestAssignmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestAssignmentRepository.findAll().size();

        // Create the RequestAssignment with an existing ID
        requestAssignment.setId(1L);
        RequestAssignmentDTO requestAssignmentDTO = requestAssignmentMapper.toDto(requestAssignment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestAssignmentMockMvc.perform(post("/api/request-assignments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestAssignmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestAssignment in the database
        List<RequestAssignment> requestAssignmentList = requestAssignmentRepository.findAll();
        assertThat(requestAssignmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequestAssignments() throws Exception {
        // Initialize the database
        requestAssignmentRepository.saveAndFlush(requestAssignment);

        // Get all the requestAssignmentList
        restRequestAssignmentMockMvc.perform(get("/api/request-assignments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestAssignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))));
    }
    
    @Test
    @Transactional
    public void getRequestAssignment() throws Exception {
        // Initialize the database
        requestAssignmentRepository.saveAndFlush(requestAssignment);

        // Get the requestAssignment
        restRequestAssignmentMockMvc.perform(get("/api/request-assignments/{id}", requestAssignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestAssignment.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)));
    }

    @Test
    @Transactional
    public void getNonExistingRequestAssignment() throws Exception {
        // Get the requestAssignment
        restRequestAssignmentMockMvc.perform(get("/api/request-assignments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestAssignment() throws Exception {
        // Initialize the database
        requestAssignmentRepository.saveAndFlush(requestAssignment);

        int databaseSizeBeforeUpdate = requestAssignmentRepository.findAll().size();

        // Update the requestAssignment
        RequestAssignment updatedRequestAssignment = requestAssignmentRepository.findById(requestAssignment.getId()).get();
        // Disconnect from session so that the updates on updatedRequestAssignment are not directly saved in db
        em.detach(updatedRequestAssignment);
        updatedRequestAssignment
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .dateCreated(UPDATED_DATE_CREATED);
        RequestAssignmentDTO requestAssignmentDTO = requestAssignmentMapper.toDto(updatedRequestAssignment);

        restRequestAssignmentMockMvc.perform(put("/api/request-assignments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestAssignmentDTO)))
            .andExpect(status().isOk());

        // Validate the RequestAssignment in the database
        List<RequestAssignment> requestAssignmentList = requestAssignmentRepository.findAll();
        assertThat(requestAssignmentList).hasSize(databaseSizeBeforeUpdate);
        RequestAssignment testRequestAssignment = requestAssignmentList.get(requestAssignmentList.size() - 1);
        assertThat(testRequestAssignment.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testRequestAssignment.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testRequestAssignment.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestAssignment() throws Exception {
        int databaseSizeBeforeUpdate = requestAssignmentRepository.findAll().size();

        // Create the RequestAssignment
        RequestAssignmentDTO requestAssignmentDTO = requestAssignmentMapper.toDto(requestAssignment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestAssignmentMockMvc.perform(put("/api/request-assignments").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestAssignmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RequestAssignment in the database
        List<RequestAssignment> requestAssignmentList = requestAssignmentRepository.findAll();
        assertThat(requestAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestAssignment() throws Exception {
        // Initialize the database
        requestAssignmentRepository.saveAndFlush(requestAssignment);

        int databaseSizeBeforeDelete = requestAssignmentRepository.findAll().size();

        // Delete the requestAssignment
        restRequestAssignmentMockMvc.perform(delete("/api/request-assignments/{id}", requestAssignment.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestAssignment> requestAssignmentList = requestAssignmentRepository.findAll();
        assertThat(requestAssignmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
