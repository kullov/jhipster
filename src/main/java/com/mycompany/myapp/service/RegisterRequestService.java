package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.RegisterRequestDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.RegisterRequest}.
 */
public interface RegisterRequestService {

    /**
     * Save a registerRequest.
     *
     * @param registerRequestDTO the entity to save.
     * @return the persisted entity.
     */
    RegisterRequestDTO save(RegisterRequestDTO registerRequestDTO);

    /**
     * Get all the registerRequests.
     *
     * @return the list of entities.
     */
    List<RegisterRequestDTO> findAll();

    /**
     * Get the "id" registerRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RegisterRequestDTO> findOne(Long id);

    /**
     * Delete the "id" registerRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
