package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AbilityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Ability}.
 */
public interface AbilityService {

    /**
     * Save a ability.
     *
     * @param abilityDTO the entity to save.
     * @return the persisted entity.
     */
    AbilityDTO save(AbilityDTO abilityDTO);

    /**
     * Get all the abilities.
     *
     * @return the list of entities.
     */
    List<AbilityDTO> findAll();

    /**
     * Get the "id" ability.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbilityDTO> findOne(Long id);

    /**
     * Delete the "id" ability.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
