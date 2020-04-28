package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.RegisterRequestService;
import com.mycompany.myapp.domain.RegisterRequest;
import com.mycompany.myapp.repository.RegisterRequestRepository;
import com.mycompany.myapp.service.dto.RegisterRequestDTO;
import com.mycompany.myapp.service.mapper.RegisterRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RegisterRequest}.
 */
@Service
@Transactional
public class RegisterRequestServiceImpl implements RegisterRequestService {

    private final Logger log = LoggerFactory.getLogger(RegisterRequestServiceImpl.class);

    private final RegisterRequestRepository registerRequestRepository;

    private final RegisterRequestMapper registerRequestMapper;

    public RegisterRequestServiceImpl(RegisterRequestRepository registerRequestRepository, RegisterRequestMapper registerRequestMapper) {
        this.registerRequestRepository = registerRequestRepository;
        this.registerRequestMapper = registerRequestMapper;
    }

    /**
     * Save a registerRequest.
     *
     * @param registerRequestDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RegisterRequestDTO save(RegisterRequestDTO registerRequestDTO) {
        log.debug("Request to save RegisterRequest : {}", registerRequestDTO);
        RegisterRequest registerRequest = registerRequestMapper.toEntity(registerRequestDTO);
        registerRequest = registerRequestRepository.save(registerRequest);
        return registerRequestMapper.toDto(registerRequest);
    }

    /**
     * Get all the registerRequests.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RegisterRequestDTO> findAll() {
        log.debug("Request to get all RegisterRequests");
        return registerRequestRepository.findAll().stream()
            .map(registerRequestMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one registerRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegisterRequestDTO> findOne(Long id) {
        log.debug("Request to get RegisterRequest : {}", id);
        return registerRequestRepository.findById(id)
            .map(registerRequestMapper::toDto);
    }

    /**
     * Delete the registerRequest by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RegisterRequest : {}", id);
        registerRequestRepository.deleteById(id);
    }
}
