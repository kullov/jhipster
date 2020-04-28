package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.Request;
import com.mycompany.myapp.repository.RequestRepository;
import com.mycompany.myapp.service.RequestService;
import com.mycompany.myapp.service.dto.RequestDTO;
import com.mycompany.myapp.service.mapper.RequestMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RequestResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequestResourceIT {

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    private static final ZonedDateTime DEFAULT_DATE_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private RequestRepository requestRepository;

    @Mock
    private RequestRepository requestRepositoryMock;

    @Autowired
    private RequestMapper requestMapper;

    @Mock
    private RequestService requestServiceMock;

    @Autowired
    private RequestService requestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .position(DEFAULT_POSITION)
            .amount(DEFAULT_AMOUNT)
            .dateCreated(DEFAULT_DATE_CREATED)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE);
        return request;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createUpdatedEntity(EntityManager em) {
        Request request = new Request()
            .position(UPDATED_POSITION)
            .amount(UPDATED_AMOUNT)
            .dateCreated(UPDATED_DATE_CREATED)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE);
        return request;
    }

    @BeforeEach
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);
        restRequestMockMvc.perform(post("/api/requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testRequest.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testRequest.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRequest.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRequest.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // Create the Request with an existing ID
        request.setId(1L);
        RequestDTO requestDTO = requestMapper.toDto(request);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc.perform(post("/api/requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc.perform(get("/api/requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(sameInstant(DEFAULT_DATE_CREATED))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRequestsWithEagerRelationshipsIsEnabled() throws Exception {
        when(requestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRequestMockMvc.perform(get("/api/requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(requestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRequestsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(requestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRequestMockMvc.perform(get("/api/requests?eagerload=true"))
            .andExpect(status().isOk());

        verify(requestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", request.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.dateCreated").value(sameInstant(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get("/api/requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .position(UPDATED_POSITION)
            .amount(UPDATED_AMOUNT)
            .dateCreated(UPDATED_DATE_CREATED)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE);
        RequestDTO requestDTO = requestMapper.toDto(updatedRequest);

        restRequestMockMvc.perform(put("/api/requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testRequest.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testRequest.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequest.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRequest.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc.perform(put("/api/requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Delete the request
        restRequestMockMvc.perform(delete("/api/requests/{id}", request.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
