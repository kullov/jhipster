package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AbilityCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AbilityCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbilityCategoryRepository extends JpaRepository<AbilityCategory, Long> {
}
