package com.footballmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballmanager.dto.TeamRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests pour le contrôleur TeamController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTeam_shouldReturnCreated() throws Exception {
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setName("OGC Nice");
        teamRequestDto.setAcronym("OGCN");
        teamRequestDto.setBudget(new BigDecimal("500000000"));

        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getTeams_shouldReturnSortedList() throws Exception {
        mockMvc.perform(get("/api/teams?sortBy=name&direction=asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}