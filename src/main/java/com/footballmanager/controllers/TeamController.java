package com.footballmanager.controllers;

import com.footballmanager.dto.TeamRequestDto;
import com.footballmanager.dto.TeamResponseDto;
import com.footballmanager.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les équipes.
 */
@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam(@Valid @RequestBody TeamRequestDto teamRequestDto) {
        return new ResponseEntity<>(teamService.createTeam(teamRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TeamResponseDto>> getTeams(
            Pageable pageable,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return new ResponseEntity<>(teamService.getTeams(pageable, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
}