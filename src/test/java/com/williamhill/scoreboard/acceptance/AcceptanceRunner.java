package com.williamhill.scoreboard.acceptance;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@KarateOptions(features = "classpath:acceptance/")
public class AcceptanceRunner {

    @Test
    void runKarateAcceptanceTests(){
        Results results = Runner.parallel(getClass(),2);
        assertEquals(0,results.getFailCount(), "Expected 0 failures, but " + results.getErrorMessages());
    }
}
