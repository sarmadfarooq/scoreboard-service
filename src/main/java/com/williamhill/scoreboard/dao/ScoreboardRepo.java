package com.williamhill.scoreboard.dao;

import com.williamhill.scoreboard.domain.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreboardRepo extends JpaRepository<Scoreboard, Integer> {
}
