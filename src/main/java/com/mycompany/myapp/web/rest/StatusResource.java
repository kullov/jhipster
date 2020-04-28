package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.StatusService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.StatusDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Status}.
 */
@RestController
@RequestMapping("/api")
public class StatusResource {

    private final Logger log = LoggerFactory.getLogger(StatusResource.class);

    private static final String ENTITY_NAME = "status";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatusService statusService;

    public StatusResource(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * {@code POST  /statuses} : Create a new status.
     *
     * @param statusDTO the statusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statusDTO, or with status {@code 400 (Bad Request)} if the status has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statuses")
    public ResponseEntity<StatusDTO> createStatus(@RequestBody StatusDTO statusDTO) throws URISyntaxException {
        log.debug("REST request to save Status : {}", statusDTO);
        if (statusDTO.getId() != null) {
            throw new BadRequestAlertException("A new status cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusDTO result = statusService.save(statusDTO);
        return ResponseEntity.created(new URI("/api/statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statuses} : Updates an existing status.
     *
     * @param statusDTO the statusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statusDTO,
     * or with status {@code 400 (Bad Request)} if the statusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statuses")
    public ResponseEntity<StatusDTO> updateStatus(@RequestBody StatusDTO statusDTO) throws URISyntaxException {
        log.debug("REST request to update Status : {}", statusDTO);
        if (statusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatusDTO result = statusService.save(statusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, statusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statuses} : get all the statuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statuses in body.
     */
    @GetMapping("/statuses")
    public List<StatusDTO> getAllStatuses() {
        log.debug("REST request to get all Statuses");
        return statusService.findAll();
    }

    /**
     * {@code GET  /statuses/:id} : get the "id" status.
     *
     * @param id the id of the statusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statuses/{id}")
    public ResponseEntity<StatusDTO> getStatus(@PathVariable Long id) {
        log.debug("REST request to get Status : {}", id);
        Optional<StatusDTO> statusDTO = statusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statusDTO);
    }

    /**
     * {@code DELETE  /statuses/:id} : delete the "id" status.
     *
     * @param id the id of the statusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statuses/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        log.debug("REST request to delete Status : {}", id);
        statusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
