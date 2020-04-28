package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.Ability;
import com.mycompany.myapp.repository.AbilityRepository;
import com.mycompany.myapp.service.AbilityService;
import com.mycompany.myapp.service.dto.AbilityDTO;
import com.mycompany.myapp.service.mapper.AbilityMapper;

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
 * Integration tests for the {@link AbilityResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AbilityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AbilityRepository abilityRepository;

    @Autowired
    private AbilityMapper abilityMapper;

    @Autowired
    private AbilityService abilityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAbilityMockMvc;

    private Ability ability;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ability createEntity(EntityManager em) {
        Ability ability = new Ability()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return ability;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ability createUpdatedEntity(EntityManager em) {
        Ability ability = new Ability()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return ability;
    }

    @BeforeEach
    public void initTest() {
        ability = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbility() throws Exception {
        int databaseSizeBeforeCreate = abilityRepository.findAll().size();

        // Create the Ability
        AbilityDTO abilityDTO = abilityMapper.toDto(ability);
        restAbilityMockMvc.perform(post("/api/abilities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityDTO)))
            .andExpect(status().isCreated());

        // Validate the Ability in the database
        List<Ability> abilityList = abilityRepository.findAll();
        assertThat(abilityList).hasSize(databaseSizeBeforeCreate + 1);
        Ability testAbility = abilityList.get(abilityList.size() - 1);
        assertThat(testAbility.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAbility.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAbilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abilityRepository.findAll().size();

        // Create the Ability with an existing ID
        ability.setId(1L);
        AbilityDTO abilityDTO = abilityMapper.toDto(ability);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbilityMockMvc.perform(post("/api/abilities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ability in the database
        List<Ability> abilityList = abilityRepository.findAll();
        assertThat(abilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAbilities() throws Exception {
        // Initialize the database
        abilityRepository.saveAndFlush(ability);

        // Get all the abilityList
        restAbilityMockMvc.perform(get("/api/abilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ability.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getAbility() throws Exception {
        // Initialize the database
        abilityRepository.saveAndFlush(ability);

        // Get the ability
        restAbilityMockMvc.perform(get("/api/abilities/{id}", ability.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ability.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingAbility() throws Exception {
        // Get the ability
        restAbilityMockMvc.perform(get("/api/abilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbility() throws Exception {
        // Initialize the database
        abilityRepository.saveAndFlush(ability);

        int databaseSizeBeforeUpdate = abilityRepository.findAll().size();

        // Update the ability
        Ability updatedAbility = abilityRepository.findById(ability.getId()).get();
        // Disconnect from session so that the updates on updatedAbility are not directly saved in db
        em.detach(updatedAbility);
        updatedAbility
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        AbilityDTO abilityDTO = abilityMapper.toDto(updatedAbility);

        restAbilityMockMvc.perform(put("/api/abilities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityDTO)))
            .andExpect(status().isOk());

        // Validate the Ability in the database
        List<Ability> abilityList = abilityRepository.findAll();
        assertThat(abilityList).hasSize(databaseSizeBeforeUpdate);
        Ability testAbility = abilityList.get(abilityList.size() - 1);
        assertThat(testAbility.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAbility.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAbility() throws Exception {
        int databaseSizeBeforeUpdate = abilityRepository.findAll().size();

        // Create the Ability
        AbilityDTO abilityDTO = abilityMapper.toDto(ability);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbilityMockMvc.perform(put("/api/abilities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ability in the database
        List<Ability> abilityList = abilityRepository.findAll();
        assertThat(abilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbility() throws Exception {
        // Initialize the database
        abilityRepository.saveAndFlush(ability);

        int databaseSizeBeforeDelete = abilityRepository.findAll().size();

        // Delete the ability
        restAbilityMockMvc.perform(delete("/api/abilities/{id}", ability.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ability> abilityList = abilityRepository.findAll();
        assertThat(abilityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
