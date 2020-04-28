package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AbilityCategoryService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.AbilityCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.AbilityCategory}.
 */
@RestController
@RequestMapping("/api")
public class AbilityCategoryResource {

    private final Logger log = LoggerFactory.getLogger(AbilityCategoryResource.class);

    private static final String ENTITY_NAME = "abilityCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AbilityCategoryService abilityCategoryService;

    public AbilityCategoryResource(AbilityCategoryService abilityCategoryService) {
        this.abilityCategoryService = abilityCategoryService;
    }

    /**
     * {@code POST  /ability-categories} : Create a new abilityCategory.
     *
     * @param abilityCategoryDTO the abilityCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new abilityCategoryDTO, or with status {@code 400 (Bad Request)} if the abilityCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ability-categories")
    public ResponseEntity<AbilityCategoryDTO> createAbilityCategory(@RequestBody AbilityCategoryDTO abilityCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save AbilityCategory : {}", abilityCategoryDTO);
        if (abilityCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new abilityCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbilityCategoryDTO result = abilityCategoryService.save(abilityCategoryDTO);
        return ResponseEntity.created(new URI("/api/ability-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ability-categories} : Updates an existing abilityCategory.
     *
     * @param abilityCategoryDTO the abilityCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abilityCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the abilityCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the abilityCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ability-categories")
    public ResponseEntity<AbilityCategoryDTO> updateAbilityCategory(@RequestBody AbilityCategoryDTO abilityCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update AbilityCategory : {}", abilityCategoryDTO);
        if (abilityCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AbilityCategoryDTO result = abilityCategoryService.save(abilityCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abilityCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ability-categories} : get all the abilityCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of abilityCategories in body.
     */
    @GetMapping("/ability-categories")
    public List<AbilityCategoryDTO> getAllAbilityCategories() {
        log.debug("REST request to get all AbilityCategories");
        return abilityCategoryService.findAll();
    }

    /**
     * {@code GET  /ability-categories/:id} : get the "id" abilityCategory.
     *
     * @param id the id of the abilityCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the abilityCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ability-categories/{id}")
    public ResponseEntity<AbilityCategoryDTO> getAbilityCategory(@PathVariable Long id) {
        log.debug("REST request to get AbilityCategory : {}", id);
        Optional<AbilityCategoryDTO> abilityCategoryDTO = abilityCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abilityCategoryDTO);
    }

    /**
     * {@code DELETE  /ability-categories/:id} : delete the "id" abilityCategory.
     *
     * @param id the id of the abilityCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ability-categories/{id}")
    public ResponseEntity<Void> deleteAbilityCategory(@PathVariable Long id) {
        log.debug("REST request to delete AbilityCategory : {}", id);
        abilityCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
