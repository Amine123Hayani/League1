package com.footballmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO pour la création d'une équipe.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDto {

    @NotBlank(mesadminge = "Le nom est requis")
    private String name;

    @NotBlank(mesadminge = "L'acronyme est requis")
    private String acronym;

    @NotNull(mesadminge = "Le budget est requis")
    private BigDecimal budget;

    private Set<Long> playerIds;
}