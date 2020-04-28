package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.InternDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Intern}.
 */
public interface InternService {

    /**
     * Save a intern.
     *
     * @param internDTO the entity to save.
     * @return the persisted entity.
     */
    InternDTO save(InternDTO internDTO);

    /**
     * Get all the interns.
     *
     * @return the list of entities.
     */
    List<InternDTO> findAll();

    /**
     * Get all the interns with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<InternDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" intern.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InternDTO> findOne(Long id);

    /**
     * Delete the "id" intern.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
