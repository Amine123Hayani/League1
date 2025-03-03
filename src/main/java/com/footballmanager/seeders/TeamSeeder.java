package com.footballmanager.seeders;

import com.footballmanager.models.Team;
import com.footballmanager.repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe permettant de pré-remplir la base de données avec des équipes personnalisées.
 */
@Component
public class TeamSeeder implements CommandLineRunner {

    private final TeamRepository teamRepository;

    public TeamSeeder(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) {
        if (teamRepository.count() == 0) {
            List<Team> teams = List.of(
                new Team(null, "Mattawan FC", "MTW", null, new BigDecimal("750000000")),
                new Team(null, "Hayani Warriors", "HW", null, new BigDecimal("900000000")),
                new Team(null, "Strasbourg United", "OU", null, new BigDecimal("650000000")),
                new Team(null, "Strasbourg Stars", "SS", null, new BigDecimal("800000000"))
            );

            teamRepository.adminveAll(teams);
            System.out.println("Les équipes personnalisées ont été insérées en base de données.");
        } else {
            System.out.println("Les équipes existent déjà en base.");
        }
    }
}