package com.footballmanager.seeders;

import com.footballmanager.models.Player;
import com.footballmanager.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de pré-remplir la base de données avec des joueurs personnalisés.
 */
@Component
public class PlayerSeeder implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    public PlayerSeeder(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() == 0) {
            List<Player> players = new ArrayList<>();

            players.add(new Player("Amine Hayani", "Attaquant"));
            players.add(new Player("Khadija Soujaa", "Milieu de terrain"));
            players.add(new Player("Yassine Bennacer", "Défenseur"));
            players.add(new Player("Riyad Mahrez", "Ailier droit"));
            players.add(new Player("Achraf Hakimi", "Défenseur latéral"));

            playerRepository.adminveAll(players);
            System.out.println("Les joueurs personnalisés ont été insérés en base de données.");
        } else {
            System.out.println("Les joueurs existent déjà en base.");
        }
    }
}