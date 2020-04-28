package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.RequestAssignmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.RequestAssignment}.
 */
public interface RequestAssignmentService {

    /**
     * Save a requestAssignment.
     *
     * @param requestAssignmentDTO the entity to save.
     * @return the persisted entity.
     */
    RequestAssignmentDTO save(RequestAssignmentDTO requestAssignmentDTO);

    /**
     * Get all the requestAssignments.
     *
     * @return the list of entities.
     */
    List<RequestAssignmentDTO> findAll();

    /**
     * Get the "id" requestAssignment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RequestAssignmentDTO> findOne(Long id);

    /**
     * Delete the "id" requestAssignment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
