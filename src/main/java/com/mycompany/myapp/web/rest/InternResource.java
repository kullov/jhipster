package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.InternService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.InternDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Intern}.
 */
@RestController
@RequestMapping("/api")
public class InternResource {

    private final Logger log = LoggerFactory.getLogger(InternResource.class);

    private static final String ENTITY_NAME = "intern";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InternService internService;

    public InternResource(InternService internService) {
        this.internService = internService;
    }

    /**
     * {@code POST  /interns} : Create a new intern.
     *
     * @param internDTO the internDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new internDTO, or with status {@code 400 (Bad Request)} if the intern has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interns")
    public ResponseEntity<InternDTO> createIntern(@RequestBody InternDTO internDTO) throws URISyntaxException {
        log.debug("REST request to save Intern : {}", internDTO);
        if (internDTO.getId() != null) {
            throw new BadRequestAlertException("A new intern cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InternDTO result = internService.save(internDTO);
        return ResponseEntity.created(new URI("/api/interns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interns} : Updates an existing intern.
     *
     * @param internDTO the internDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated internDTO,
     * or with status {@code 400 (Bad Request)} if the internDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the internDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interns")
    public ResponseEntity<InternDTO> updateIntern(@RequestBody InternDTO internDTO) throws URISyntaxException {
        log.debug("REST request to update Intern : {}", internDTO);
        if (internDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InternDTO result = internService.save(internDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, internDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /interns} : get all the interns.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interns in body.
     */
    @GetMapping("/interns")
    public List<InternDTO> getAllInterns(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Interns");
        return internService.findAll();
    }

    /**
     * {@code GET  /interns/:id} : get the "id" intern.
     *
     * @param id the id of the internDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the internDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interns/{id}")
    public ResponseEntity<InternDTO> getIntern(@PathVariable Long id) {
        log.debug("REST request to get Intern : {}", id);
        Optional<InternDTO> internDTO = internService.findOne(id);
        return ResponseUtil.wrapOrNotFound(internDTO);
    }

    /**
     * {@code DELETE  /interns/:id} : delete the "id" intern.
     *
     * @param id the id of the internDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interns/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable Long id) {
        log.debug("REST request to delete Intern : {}", id);
        internService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
