package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AbilityCategoryService;
import com.mycompany.myapp.domain.AbilityCategory;
import com.mycompany.myapp.repository.AbilityCategoryRepository;
import com.mycompany.myapp.service.dto.AbilityCategoryDTO;
import com.mycompany.myapp.service.mapper.AbilityCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AbilityCategory}.
 */
@Service
@Transactional
public class AbilityCategoryServiceImpl implements AbilityCategoryService {

    private final Logger log = LoggerFactory.getLogger(AbilityCategoryServiceImpl.class);

    private final AbilityCategoryRepository abilityCategoryRepository;

    private final AbilityCategoryMapper abilityCategoryMapper;

    public AbilityCategoryServiceImpl(AbilityCategoryRepository abilityCategoryRepository, AbilityCategoryMapper abilityCategoryMapper) {
        this.abilityCategoryRepository = abilityCategoryRepository;
        this.abilityCategoryMapper = abilityCategoryMapper;
    }

    /**
     * Save a abilityCategory.
     *
     * @param abilityCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AbilityCategoryDTO save(AbilityCategoryDTO abilityCategoryDTO) {
        log.debug("Request to save AbilityCategory : {}", abilityCategoryDTO);
        AbilityCategory abilityCategory = abilityCategoryMapper.toEntity(abilityCategoryDTO);
        abilityCategory = abilityCategoryRepository.save(abilityCategory);
        return abilityCategoryMapper.toDto(abilityCategory);
    }

    /**
     * Get all the abilityCategories.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AbilityCategoryDTO> findAll() {
        log.debug("Request to get all AbilityCategories");
        return abilityCategoryRepository.findAll().stream()
            .map(abilityCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one abilityCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AbilityCategoryDTO> findOne(Long id) {
        log.debug("Request to get AbilityCategory : {}", id);
        return abilityCategoryRepository.findById(id)
            .map(abilityCategoryMapper::toDto);
    }

    /**
     * Delete the abilityCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AbilityCategory : {}", id);
        abilityCategoryRepository.deleteById(id);
    }
}
