package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AApp;
import com.mycompany.myapp.domain.AbilityCategory;
import com.mycompany.myapp.repository.AbilityCategoryRepository;
import com.mycompany.myapp.service.AbilityCategoryService;
import com.mycompany.myapp.service.dto.AbilityCategoryDTO;
import com.mycompany.myapp.service.mapper.AbilityCategoryMapper;

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
 * Integration tests for the {@link AbilityCategoryResource} REST controller.
 */
@SpringBootTest(classes = AApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AbilityCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private AbilityCategoryRepository abilityCategoryRepository;

    @Autowired
    private AbilityCategoryMapper abilityCategoryMapper;

    @Autowired
    private AbilityCategoryService abilityCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAbilityCategoryMockMvc;

    private AbilityCategory abilityCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbilityCategory createEntity(EntityManager em) {
        AbilityCategory abilityCategory = new AbilityCategory()
            .name(DEFAULT_NAME);
        return abilityCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AbilityCategory createUpdatedEntity(EntityManager em) {
        AbilityCategory abilityCategory = new AbilityCategory()
            .name(UPDATED_NAME);
        return abilityCategory;
    }

    @BeforeEach
    public void initTest() {
        abilityCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbilityCategory() throws Exception {
        int databaseSizeBeforeCreate = abilityCategoryRepository.findAll().size();

        // Create the AbilityCategory
        AbilityCategoryDTO abilityCategoryDTO = abilityCategoryMapper.toDto(abilityCategory);
        restAbilityCategoryMockMvc.perform(post("/api/ability-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the AbilityCategory in the database
        List<AbilityCategory> abilityCategoryList = abilityCategoryRepository.findAll();
        assertThat(abilityCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        AbilityCategory testAbilityCategory = abilityCategoryList.get(abilityCategoryList.size() - 1);
        assertThat(testAbilityCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createAbilityCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abilityCategoryRepository.findAll().size();

        // Create the AbilityCategory with an existing ID
        abilityCategory.setId(1L);
        AbilityCategoryDTO abilityCategoryDTO = abilityCategoryMapper.toDto(abilityCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbilityCategoryMockMvc.perform(post("/api/ability-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbilityCategory in the database
        List<AbilityCategory> abilityCategoryList = abilityCategoryRepository.findAll();
        assertThat(abilityCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAbilityCategories() throws Exception {
        // Initialize the database
        abilityCategoryRepository.saveAndFlush(abilityCategory);

        // Get all the abilityCategoryList
        restAbilityCategoryMockMvc.perform(get("/api/ability-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abilityCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getAbilityCategory() throws Exception {
        // Initialize the database
        abilityCategoryRepository.saveAndFlush(abilityCategory);

        // Get the abilityCategory
        restAbilityCategoryMockMvc.perform(get("/api/ability-categories/{id}", abilityCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(abilityCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingAbilityCategory() throws Exception {
        // Get the abilityCategory
        restAbilityCategoryMockMvc.perform(get("/api/ability-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbilityCategory() throws Exception {
        // Initialize the database
        abilityCategoryRepository.saveAndFlush(abilityCategory);

        int databaseSizeBeforeUpdate = abilityCategoryRepository.findAll().size();

        // Update the abilityCategory
        AbilityCategory updatedAbilityCategory = abilityCategoryRepository.findById(abilityCategory.getId()).get();
        // Disconnect from session so that the updates on updatedAbilityCategory are not directly saved in db
        em.detach(updatedAbilityCategory);
        updatedAbilityCategory
            .name(UPDATED_NAME);
        AbilityCategoryDTO abilityCategoryDTO = abilityCategoryMapper.toDto(updatedAbilityCategory);

        restAbilityCategoryMockMvc.perform(put("/api/ability-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the AbilityCategory in the database
        List<AbilityCategory> abilityCategoryList = abilityCategoryRepository.findAll();
        assertThat(abilityCategoryList).hasSize(databaseSizeBeforeUpdate);
        AbilityCategory testAbilityCategory = abilityCategoryList.get(abilityCategoryList.size() - 1);
        assertThat(testAbilityCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingAbilityCategory() throws Exception {
        int databaseSizeBeforeUpdate = abilityCategoryRepository.findAll().size();

        // Create the AbilityCategory
        AbilityCategoryDTO abilityCategoryDTO = abilityCategoryMapper.toDto(abilityCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbilityCategoryMockMvc.perform(put("/api/ability-categories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abilityCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AbilityCategory in the database
        List<AbilityCategory> abilityCategoryList = abilityCategoryRepository.findAll();
        assertThat(abilityCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbilityCategory() throws Exception {
        // Initialize the database
        abilityCategoryRepository.saveAndFlush(abilityCategory);

        int databaseSizeBeforeDelete = abilityCategoryRepository.findAll().size();

        // Delete the abilityCategory
        restAbilityCategoryMockMvc.perform(delete("/api/ability-categories/{id}", abilityCategory.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AbilityCategory> abilityCategoryList = abilityCategoryRepository.findAll();
        assertThat(abilityCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
