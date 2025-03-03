package com.footballmanager.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO de réponse pour une équipe, incluant les joueurs associés.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponseDto {
    private Long id;
    private String name;
    private String acronym;
    private BigDecimal budget;
    private Set<PlayerDto> players; // Ajout des joueurs dans la réponse
}