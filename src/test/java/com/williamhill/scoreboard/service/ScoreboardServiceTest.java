package com.williamhill.scoreboard.service;

import com.williamhill.scoreboard.dao.ScoreboardRepo;
import com.williamhill.scoreboard.domain.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreboardServiceTest {

    private ScoreboardService scoreboardService;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private ScoreboardRepo scoreboardRepo;
    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private SimpMessagingTemplate simpMessagingTemplate;

    @BeforeEach
    void setUp() {
        scoreboardService = new ScoreboardService(scoreboardRepo, simpMessagingTemplate);
    }

    @Test
    void testFindById() {
        Scoreboard scoreboard = new Scoreboard(1, "EventName", 1, 2);

        when(scoreboardRepo.findById(1)).thenReturn(Optional.of(scoreboard));
        Optional<Scoreboard> actual = scoreboardService.findById(1);
        assertTrue(actual.isPresent());
        assertEquals(scoreboard, actual.get());
    }

    @Test
    void testFindAll() {
        Scoreboard scoreboard1 = new Scoreboard(1, "EventName 1", 1, 2);
        Scoreboard scoreboard2 = new Scoreboard(1, "EventName 2", 3, 2);

        when(scoreboardRepo.findAll()).thenReturn(Arrays.asList(scoreboard1,scoreboard2));
        List<Scoreboard> actual  = scoreboardService.findAll();

        assertEquals(2, actual.size());
        assertEquals(scoreboard1, actual.get(0));
        assertEquals(scoreboard2, actual.get(1));
    }

    @Test
    void createOrUpdateScoreboard() {
        Scoreboard scoreboard = new Scoreboard(1, "EventName", 1, 2);

        when(scoreboardRepo.saveAndFlush(scoreboard)).thenReturn(scoreboard);
        Scoreboard actual = scoreboardService.createOrUpdateScoreboard(scoreboard);
        assertNotNull(actual);
        assertEquals(scoreboard, actual);
    }
}