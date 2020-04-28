package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.RequestAssignmentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.RequestAssignmentDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.RequestAssignment}.
 */
@RestController
@RequestMapping("/api")
public class RequestAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(RequestAssignmentResource.class);

    private static final String ENTITY_NAME = "requestAssignment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestAssignmentService requestAssignmentService;

    public RequestAssignmentResource(RequestAssignmentService requestAssignmentService) {
        this.requestAssignmentService = requestAssignmentService;
    }

    /**
     * {@code POST  /request-assignments} : Create a new requestAssignment.
     *
     * @param requestAssignmentDTO the requestAssignmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestAssignmentDTO, or with status {@code 400 (Bad Request)} if the requestAssignment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-assignments")
    public ResponseEntity<RequestAssignmentDTO> createRequestAssignment(@RequestBody RequestAssignmentDTO requestAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to save RequestAssignment : {}", requestAssignmentDTO);
        if (requestAssignmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestAssignment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestAssignmentDTO result = requestAssignmentService.save(requestAssignmentDTO);
        return ResponseEntity.created(new URI("/api/request-assignments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-assignments} : Updates an existing requestAssignment.
     *
     * @param requestAssignmentDTO the requestAssignmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestAssignmentDTO,
     * or with status {@code 400 (Bad Request)} if the requestAssignmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestAssignmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-assignments")
    public ResponseEntity<RequestAssignmentDTO> updateRequestAssignment(@RequestBody RequestAssignmentDTO requestAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to update RequestAssignment : {}", requestAssignmentDTO);
        if (requestAssignmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestAssignmentDTO result = requestAssignmentService.save(requestAssignmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, requestAssignmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-assignments} : get all the requestAssignments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestAssignments in body.
     */
    @GetMapping("/request-assignments")
    public List<RequestAssignmentDTO> getAllRequestAssignments() {
        log.debug("REST request to get all RequestAssignments");
        return requestAssignmentService.findAll();
    }

    /**
     * {@code GET  /request-assignments/:id} : get the "id" requestAssignment.
     *
     * @param id the id of the requestAssignmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestAssignmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-assignments/{id}")
    public ResponseEntity<RequestAssignmentDTO> getRequestAssignment(@PathVariable Long id) {
        log.debug("REST request to get RequestAssignment : {}", id);
        Optional<RequestAssignmentDTO> requestAssignmentDTO = requestAssignmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestAssignmentDTO);
    }

    /**
     * {@code DELETE  /request-assignments/:id} : delete the "id" requestAssignment.
     *
     * @param id the id of the requestAssignmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-assignments/{id}")
    public ResponseEntity<Void> deleteRequestAssignment(@PathVariable Long id) {
        log.debug("REST request to delete RequestAssignment : {}", id);
        requestAssignmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
