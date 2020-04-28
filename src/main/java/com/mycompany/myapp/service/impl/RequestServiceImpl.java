package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.RequestService;
import com.mycompany.myapp.domain.Request;
import com.mycompany.myapp.repository.RequestRepository;
import com.mycompany.myapp.service.dto.RequestDTO;
import com.mycompany.myapp.service.mapper.RequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Request}.
 */
@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    /**
     * Save a request.
     *
     * @param requestDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RequestDTO save(RequestDTO requestDTO) {
        log.debug("Request to save Request : {}", requestDTO);
        Request request = requestMapper.toEntity(requestDTO);
        request = requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    /**
     * Get all the requests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requests");
        return requestRepository.findAll(pageable)
            .map(requestMapper::toDto);
    }

    /**
     * Get all the requests with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RequestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestRepository.findAllWithEagerRelationships(pageable).map(requestMapper::toDto);
    }

    /**
     * Get one request by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestDTO> findOne(Long id) {
        log.debug("Request to get Request : {}", id);
        return requestRepository.findOneWithEagerRelationships(id)
            .map(requestMapper::toDto);
    }

    /**
     * Delete the request by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Request : {}", id);
        requestRepository.deleteById(id);
    }
}
