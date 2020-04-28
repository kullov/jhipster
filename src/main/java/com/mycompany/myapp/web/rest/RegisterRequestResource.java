package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.RegisterRequestService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.RegisterRequestDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.RegisterRequest}.
 */
@RestController
@RequestMapping("/api")
public class RegisterRequestResource {

    private final Logger log = LoggerFactory.getLogger(RegisterRequestResource.class);

    private static final String ENTITY_NAME = "registerRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegisterRequestService registerRequestService;

    public RegisterRequestResource(RegisterRequestService registerRequestService) {
        this.registerRequestService = registerRequestService;
    }

    /**
     * {@code POST  /register-requests} : Create a new registerRequest.
     *
     * @param registerRequestDTO the registerRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registerRequestDTO, or with status {@code 400 (Bad Request)} if the registerRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/register-requests")
    public ResponseEntity<RegisterRequestDTO> createRegisterRequest(@RequestBody RegisterRequestDTO registerRequestDTO) throws URISyntaxException {
        log.debug("REST request to save RegisterRequest : {}", registerRequestDTO);
        if (registerRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new registerRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegisterRequestDTO result = registerRequestService.save(registerRequestDTO);
        return ResponseEntity.created(new URI("/api/register-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /register-requests} : Updates an existing registerRequest.
     *
     * @param registerRequestDTO the registerRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registerRequestDTO,
     * or with status {@code 400 (Bad Request)} if the registerRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the registerRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/register-requests")
    public ResponseEntity<RegisterRequestDTO> updateRegisterRequest(@RequestBody RegisterRequestDTO registerRequestDTO) throws URISyntaxException {
        log.debug("REST request to update RegisterRequest : {}", registerRequestDTO);
        if (registerRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegisterRequestDTO result = registerRequestService.save(registerRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, registerRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /register-requests} : get all the registerRequests.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of registerRequests in body.
     */
    @GetMapping("/register-requests")
    public List<RegisterRequestDTO> getAllRegisterRequests() {
        log.debug("REST request to get all RegisterRequests");
        return registerRequestService.findAll();
    }

    /**
     * {@code GET  /register-requests/:id} : get the "id" registerRequest.
     *
     * @param id the id of the registerRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the registerRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/register-requests/{id}")
    public ResponseEntity<RegisterRequestDTO> getRegisterRequest(@PathVariable Long id) {
        log.debug("REST request to get RegisterRequest : {}", id);
        Optional<RegisterRequestDTO> registerRequestDTO = registerRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registerRequestDTO);
    }

    /**
     * {@code DELETE  /register-requests/:id} : delete the "id" registerRequest.
     *
     * @param id the id of the registerRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/register-requests/{id}")
    public ResponseEntity<Void> deleteRegisterRequest(@PathVariable Long id) {
        log.debug("REST request to delete RegisterRequest : {}", id);
        registerRequestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
