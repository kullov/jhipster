package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Ability;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ability entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbilityRepository extends JpaRepository<Ability, Long> {
}
