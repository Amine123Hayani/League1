package com.footballmanager.services;

import com.footballmanager.dto.TeamRequestDto;
import com.footballmanager.dto.TeamResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface de service pour la gestion des équipes.
 */
public interface TeamService {
    TeamResponseDto createTeam(TeamRequestDto teamRequestDto);
    Page<TeamResponseDto> getTeams(Pageable pageable, String sortBy, String direction);
}