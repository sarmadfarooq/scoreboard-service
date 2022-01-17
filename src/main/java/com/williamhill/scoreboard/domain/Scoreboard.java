package com.williamhill.scoreboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Scoreboard {
    @Id
    @GeneratedValue
    private int id;
    private String eventTitle;
    private int teamAScore;
    private int teamBScore;

    //TODO: DOuble check
    @Transient
    @JsonIgnore
    public String getScore() {
        return teamAScore + "-" + teamBScore;
    }
}
