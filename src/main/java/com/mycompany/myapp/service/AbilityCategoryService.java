package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AbilityCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.AbilityCategory}.
 */
public interface AbilityCategoryService {

    /**
     * Save a abilityCategory.
     *
     * @param abilityCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    AbilityCategoryDTO save(AbilityCategoryDTO abilityCategoryDTO);

    /**
     * Get all the abilityCategories.
     *
     * @return the list of entities.
     */
    List<AbilityCategoryDTO> findAll();

    /**
     * Get the "id" abilityCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbilityCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" abilityCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
