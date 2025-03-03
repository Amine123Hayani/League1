package com.footballmanager.dto;

import lombok.*;

/**
 * DTO pour un joueur.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String name;
    private String position;
}