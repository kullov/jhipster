package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.Intern;
import com.mycompany.myapp.repository.InternRepository;
import com.mycompany.myapp.service.InternService;
import com.mycompany.myapp.service.dto.InternDTO;
import com.mycompany.myapp.service.mapper.InternMapper;

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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InternResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InternResourceIT {

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_BIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_BIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JOIN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOIN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CLASS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHONE = 1;
    private static final Integer UPDATED_PHONE = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private InternRepository internRepository;

    @Mock
    private InternRepository internRepositoryMock;

    @Autowired
    private InternMapper internMapper;

    @Mock
    private InternService internServiceMock;

    @Autowired
    private InternService internService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInternMockMvc;

    private Intern intern;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Intern createEntity(EntityManager em) {
        Intern intern = new Intern()
            .code(DEFAULT_CODE)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .joinDate(DEFAULT_JOIN_DATE)
            .className(DEFAULT_CLASS_NAME)
            .avatar(DEFAULT_AVATAR)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS);
        return intern;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Intern createUpdatedEntity(EntityManager em) {
        Intern intern = new Intern()
            .code(UPDATED_CODE)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .joinDate(UPDATED_JOIN_DATE)
            .className(UPDATED_CLASS_NAME)
            .avatar(UPDATED_AVATAR)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS);
        return intern;
    }

    @BeforeEach
    public void initTest() {
        intern = createEntity(em);
    }

    @Test
    @Transactional
    public void createIntern() throws Exception {
        int databaseSizeBeforeCreate = internRepository.findAll().size();

        // Create the Intern
        InternDTO internDTO = internMapper.toDto(intern);
        restInternMockMvc.perform(post("/api/interns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internDTO)))
            .andExpect(status().isCreated());

        // Validate the Intern in the database
        List<Intern> internList = internRepository.findAll();
        assertThat(internList).hasSize(databaseSizeBeforeCreate + 1);
        Intern testIntern = internList.get(internList.size() - 1);
        assertThat(testIntern.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testIntern.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testIntern.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testIntern.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testIntern.getJoinDate()).isEqualTo(DEFAULT_JOIN_DATE);
        assertThat(testIntern.getClassName()).isEqualTo(DEFAULT_CLASS_NAME);
        assertThat(testIntern.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testIntern.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testIntern.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testIntern.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testIntern.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIntern.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createInternWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = internRepository.findAll().size();

        // Create the Intern with an existing ID
        intern.setId(1L);
        InternDTO internDTO = internMapper.toDto(intern);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInternMockMvc.perform(post("/api/interns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Intern in the database
        List<Intern> internList = internRepository.findAll();
        assertThat(internList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInterns() throws Exception {
        // Initialize the database
        internRepository.saveAndFlush(intern);

        // Get all the internList
        restInternMockMvc.perform(get("/api/interns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(intern.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].joinDate").value(hasItem(DEFAULT_JOIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllInternsWithEagerRelationshipsIsEnabled() throws Exception {
        when(internServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInternMockMvc.perform(get("/api/interns?eagerload=true"))
            .andExpect(status().isOk());

        verify(internServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllInternsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(internServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInternMockMvc.perform(get("/api/interns?eagerload=true"))
            .andExpect(status().isOk());

        verify(internServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getIntern() throws Exception {
        // Initialize the database
        internRepository.saveAndFlush(intern);

        // Get the intern
        restInternMockMvc.perform(get("/api/interns/{id}", intern.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(intern.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.joinDate").value(DEFAULT_JOIN_DATE.toString()))
            .andExpect(jsonPath("$.className").value(DEFAULT_CLASS_NAME))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    public void getNonExistingIntern() throws Exception {
        // Get the intern
        restInternMockMvc.perform(get("/api/interns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIntern() throws Exception {
        // Initialize the database
        internRepository.saveAndFlush(intern);

        int databaseSizeBeforeUpdate = internRepository.findAll().size();

        // Update the intern
        Intern updatedIntern = internRepository.findById(intern.getId()).get();
        // Disconnect from session so that the updates on updatedIntern are not directly saved in db
        em.detach(updatedIntern);
        updatedIntern
            .code(UPDATED_CODE)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .joinDate(UPDATED_JOIN_DATE)
            .className(UPDATED_CLASS_NAME)
            .avatar(UPDATED_AVATAR)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS);
        InternDTO internDTO = internMapper.toDto(updatedIntern);

        restInternMockMvc.perform(put("/api/interns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internDTO)))
            .andExpect(status().isOk());

        // Validate the Intern in the database
        List<Intern> internList = internRepository.findAll();
        assertThat(internList).hasSize(databaseSizeBeforeUpdate);
        Intern testIntern = internList.get(internList.size() - 1);
        assertThat(testIntern.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testIntern.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testIntern.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testIntern.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testIntern.getJoinDate()).isEqualTo(UPDATED_JOIN_DATE);
        assertThat(testIntern.getClassName()).isEqualTo(UPDATED_CLASS_NAME);
        assertThat(testIntern.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testIntern.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testIntern.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testIntern.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testIntern.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIntern.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingIntern() throws Exception {
        int databaseSizeBeforeUpdate = internRepository.findAll().size();

        // Create the Intern
        InternDTO internDTO = internMapper.toDto(intern);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInternMockMvc.perform(put("/api/interns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Intern in the database
        List<Intern> internList = internRepository.findAll();
        assertThat(internList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIntern() throws Exception {
        // Initialize the database
        internRepository.saveAndFlush(intern);

        int databaseSizeBeforeDelete = internRepository.findAll().size();

        // Delete the intern
        restInternMockMvc.perform(delete("/api/interns/{id}", intern.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Intern> internList = internRepository.findAll();
        assertThat(internList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
