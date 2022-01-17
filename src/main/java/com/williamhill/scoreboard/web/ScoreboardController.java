package com.williamhill.scoreboard.web;

import com.williamhill.scoreboard.domain.Scoreboard;
import com.williamhill.scoreboard.service.ScoreboardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * Rest controller
 */
@Slf4j
@RestController
@RequestMapping(value = "/")
public class ScoreboardController {

    private ScoreboardService scoreboardService;


    @Autowired
    public ScoreboardController(ScoreboardService scoreboardService) {
        this.scoreboardService = scoreboardService;
    }

    @ApiOperation(value = "Operation to get a specific Scoreboard by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No data available")}
    )
    @GetMapping("/scoreboard/{id}")
    public ResponseEntity<Scoreboard> getScoreboardById(@PathVariable("id") int id) {
        Optional<Scoreboard> scoreboard = scoreboardService.findById(id);
        if (scoreboard.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scoreboard.get());
        }
    }

    @ApiOperation(value = "Operation to list all Scoreboard")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No data available")}
    )
    @GetMapping("/scoreboards")
    public ResponseEntity<List<Scoreboard>> getAllScoreboards() {
        log.info("Retrieving list of all scoreboards");
        List<Scoreboard> scoreboards = scoreboardService.findAll();
        if (scoreboards == null || scoreboards.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(scoreboards);
        }
    }

    @ApiOperation(value = "Operation to create a new Scoreboard. Newly created Scoreboard is returned in response.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server Error")}
    )
    @PostMapping("/scoreboard")
    public ResponseEntity<Scoreboard> createScoreboard(@RequestBody Scoreboard scoreboard) {
        log.info("Creating a new scoreboards");
        if (scoreboard.getId()!= 0 ) {
            log.error("Scoreboard id should be 0 in request body");
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        try {
            Scoreboard scoreboardNew = scoreboardService.createOrUpdateScoreboard(scoreboard);
            return ResponseEntity.ok(scoreboardNew);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Operation to update a new Scoreboard. Updated Scoreboard is returned in response.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad input request"),
            @ApiResponse(code = 404, message = "Unable to find Scoreboard"),
            @ApiResponse(code = 500, message = "Server Error")}
    )
    @PutMapping("/scoreboard/{id}")
    public ResponseEntity<Scoreboard> updateScoreboard(@RequestBody Scoreboard scoreboard, @NotNull @PathVariable("id") int id) {
        log.info("Updating scoreboard with id {}", id);
        if (id == 0 || id != scoreboard.getId()) {
            log.error("Scoreboard id in path and request body do not match");
            return ResponseEntity.status(BAD_REQUEST).build();
        }
        try {
            Scoreboard scoreboardUpdated = scoreboardService.createOrUpdateScoreboard(scoreboard);
            return ResponseEntity.ok(scoreboardUpdated);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
