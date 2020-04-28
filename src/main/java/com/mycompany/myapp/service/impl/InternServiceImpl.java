package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.InternService;
import com.mycompany.myapp.domain.Intern;
import com.mycompany.myapp.repository.InternRepository;
import com.mycompany.myapp.service.dto.InternDTO;
import com.mycompany.myapp.service.mapper.InternMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Intern}.
 */
@Service
@Transactional
public class InternServiceImpl implements InternService {

    private final Logger log = LoggerFactory.getLogger(InternServiceImpl.class);

    private final InternRepository internRepository;

    private final InternMapper internMapper;

    public InternServiceImpl(InternRepository internRepository, InternMapper internMapper) {
        this.internRepository = internRepository;
        this.internMapper = internMapper;
    }

    /**
     * Save a intern.
     *
     * @param internDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InternDTO save(InternDTO internDTO) {
        log.debug("Request to save Intern : {}", internDTO);
        Intern intern = internMapper.toEntity(internDTO);
        intern = internRepository.save(intern);
        return internMapper.toDto(intern);
    }

    /**
     * Get all the interns.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InternDTO> findAll() {
        log.debug("Request to get all Interns");
        return internRepository.findAllWithEagerRelationships().stream()
            .map(internMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the interns with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<InternDTO> findAllWithEagerRelationships(Pageable pageable) {
        return internRepository.findAllWithEagerRelationships(pageable).map(internMapper::toDto);
    }

    /**
     * Get one intern by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InternDTO> findOne(Long id) {
        log.debug("Request to get Intern : {}", id);
        return internRepository.findOneWithEagerRelationships(id)
            .map(internMapper::toDto);
    }

    /**
     * Delete the intern by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Intern : {}", id);
        internRepository.deleteById(id);
    }
}
