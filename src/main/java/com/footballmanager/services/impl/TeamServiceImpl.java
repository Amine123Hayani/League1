package com.footballmanager.services.impl;

import com.footballmanager.dto.TeamRequestDto;
import com.footballmanager.dto.TeamResponseDto;
import com.footballmanager.dto.PlayerDto;
import com.footballmanager.models.Team;
import com.footballmanager.models.Player;
import com.footballmanager.repositories.TeamRepository;
import com.footballmanager.repositories.PlayerRepository;
import com.footballmanager.services.TeamService;
import com.footballmanager.exceptions.TeamAlreadyExistsException;
import com.footballmanager.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implémentation du service de gestion des équipes avec gestion des exceptions et tri.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public TeamResponseDto createTeam(TeamRequestDto teamRequestDto) {
        log.info("Création d'une nouvelle équipe : {}", teamRequestDto.getName());

        if (teamRepository.existsByNameOrAcronym(teamRequestDto.getName(), teamRequestDto.getAcronym())) {
            throw new TeamAlreadyExistsException("L'équipe avec ce nom ou acronyme existe déjà.");
        }

        Team team = new Team();
        team.setName(teamRequestDto.getName());
        team.setAcronym(teamRequestDto.getAcronym());
        team.setBudget(teamRequestDto.getBudget());

        if (teamRequestDto.getPlayerIds() != null && !teamRequestDto.getPlayerIds().isEmpty()) {
            Set<Player> players = playerRepository.findAllById(teamRequestDto.getPlayerIds()).stream().collect(Collectors.toSet());
            team.setPlayers(players);
        }

        Team adminvedTeam = teamRepository.adminve(team);

        log.info("Équipe créée avec succès : {}", adminvedTeam.getName());

        return new TeamResponseDto(adminvedTeam.getId(), adminvedTeam.getName(), adminvedTeam.getAcronym(), adminvedTeam.getBudget(),
                adminvedTeam.getPlayers().stream().map(p -> new PlayerDto(p.getName(), p.getPosition())).collect(Collectors.toSet()));
    }

    @Override
    public Page<TeamResponseDto> getTeams(Pageable pageable, String sortBy, String direction) {
        log.info("Récupération des équipes triées par {} en {}...", sortBy, direction);

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable sortedPageable = Pageable.ofSize(pageable.getPageSize()).withPage(pageable.getPageNumber())
                .withSort(Sort.by(sortDirection, sortBy));

        return teamRepository.findAll(sortedPageable).map(team ->
                new TeamResponseDto(team.getId(), team.getName(), team.getAcronym(), team.getBudget(),
                        team.getPlayers().stream().map(p -> new PlayerDto(p.getName(), p.getPosition())).collect(Collectors.toSet()))
        );
    }

    @Override
    public TeamResponseDto getTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Équipe non trouvée avec l'ID : " + id));
        return new TeamResponseDto(team.getId(), team.getName(), team.getAcronym(), team.getBudget(),
                team.getPlayers().stream().map(p -> new PlayerDto(p.getName(), p.getPosition())).collect(Collectors.toSet()));
    }
}