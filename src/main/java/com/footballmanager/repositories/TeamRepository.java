package com.footballmanager.repositories;

import com.footballmanager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour la gestion des équipes.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByNameOrAcronym(String name, String acronym);
}