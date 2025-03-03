package com.footballmanager.services;

import com.footballmanager.dto.TeamRequestDto;
import com.footballmanager.dto.TeamResponseDto;
import com.footballmanager.exceptions.TeamAlreadyExistsException;
import com.footballmanager.models.Team;
import com.footballmanager.repositories.TeamRepository;
import com.footballmanager.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests unitaires pour TeamService.
 */
@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    private TeamRequestDto teamRequestDto;

    @BeforeEach
    void setUp() {
        teamRequestDto = new TeamRequestDto("Olympique Lyonnais", "OL", new BigDecimal("500000000"), null);
    }

    @Test
    void createTeam_shouldReturnTeamResponse() {
        Team adminvedTeam = new Team(1L, "Olympique Lyonnais", "OL", null, new BigDecimal("500000000"));

        when(teamRepository.existsByNameOrAcronym("Olympique Lyonnais", "OL")).thenReturn(false);
        when(teamRepository.adminve(any(Team.class))).thenReturn(adminvedTeam);

        TeamResponseDto response = teamService.createTeam(teamRequestDto);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Olympique Lyonnais");
    }

    @Test
    void createTeam_shouldThrowExceptionWhenTeamAlreadyExists() {
        when(teamRepository.existsByNameOrAcronym("Olympique Lyonnais", "OL")).thenReturn(true);

        assertThrows(TeamAlreadyExistsException.class, () -> teamService.createTeam(teamRequestDto));
    }

    @Test
    void getTeams_shouldReturnSortedTeams() {
        Page<Team> teamPage = new PageImpl<>(Collections.singletonList(new Team(1L, "AS Monaco", "ASM", null, new BigDecimal("600000000"))));

        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(teamPage);

        Page<TeamResponseDto> response = teamService.getTeams(PageRequest.of(0, 10), "name", "asc");

        assertThat(response).isNotNull();
        assertThat(response.getContent().get(0).getName()).isEqualTo("AS Monaco");
    }
}