package com.williamhill.scoreboard.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamhill.scoreboard.domain.Scoreboard;
import com.williamhill.scoreboard.service.ScoreboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ScoreboardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScoreboardService scoreboardService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetScoreboardById() throws Exception {
        Scoreboard scoreboard = new Scoreboard(123, "EventName", 1, 2);

        when(scoreboardService.findById(123)).thenReturn(Optional.of(scoreboard));
        mockMvc.perform(get("/scoreboard/{id}", 123).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetScoreboardById_NoContent() throws Exception {

        when(scoreboardService.findById(123)).thenReturn(Optional.empty());
        mockMvc.perform(get("/scoreboard/{id}", 123).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllScoreboards() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        Scoreboard scoreboard2 = new Scoreboard(1, "EventName 2", 3, 2);

        when(scoreboardService.findAll()).thenReturn(Arrays.asList(scoreboard1, scoreboard2));
        mockMvc.perform(get("/scoreboards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllScoreboards_NoContent() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        Scoreboard scoreboard2 = new Scoreboard(1, "EventName 2", 3, 2);

        when(scoreboardService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/scoreboards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void createScoreboard() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(0, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenReturn(scoreboard1);
        mockMvc.perform(post("/scoreboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isOk());
    }

    @Test
    void createScoreboard_BadRequest() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(10, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenReturn(scoreboard1);
        mockMvc.perform(post("/scoreboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void createScoreboard__InternalServerError() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(0, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(post("/scoreboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isInternalServerError());
    }

    @Test
    void testUpdateScoreboard() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenReturn(scoreboard1);
        mockMvc.perform(put("/scoreboard/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isOk());
    }

    @Test
    void testUpdateScoreboard_BadRequest() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenReturn(scoreboard1);
        mockMvc.perform(put("/scoreboard/{id}", "123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testUpdateScoreboard_InternalServerError() throws Exception {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(scoreboard1);
        when(scoreboardService.createOrUpdateScoreboard(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(put("/scoreboard/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        ).andExpect(status().isInternalServerError());
    }
}