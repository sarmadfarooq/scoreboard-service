package com.williamhill.scoreboard.service;

import com.williamhill.scoreboard.dao.ScoreboardRepo;
import com.williamhill.scoreboard.domain.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreboardService {
    private static final String DESTINATION = "/topic/pushNotification";
    private ScoreboardRepo scoreboardRepo;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ScoreboardService(ScoreboardRepo scoreboardRepo, SimpMessagingTemplate simpMessagingTemplate) {
        this.scoreboardRepo = scoreboardRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public Optional<Scoreboard> findById(int id) {
        return scoreboardRepo.findById(id);
    }

    public List<Scoreboard> findAll() {
        return scoreboardRepo.findAll();
    }

    public Scoreboard createOrUpdateScoreboard(Scoreboard scoreboard) {
        Scoreboard newScoreboard = scoreboardRepo.saveAndFlush(scoreboard);
//        simpMessagingTemplate.convertAndSend(DESTINATION, "New update: " + newScoreboard.getEventTitle() + " " + newScoreboard.getScore());
        simpMessagingTemplate.convertAndSend(DESTINATION, "New update: " + newScoreboard.getEventTitle() + " " + newScoreboard.getScore());
        return newScoreboard;
    }
}
