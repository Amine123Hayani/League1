package com.footballmanager.repositories;

import com.footballmanager.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour la gestion des joueurs.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}