package com.ligo.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

public class ApiRunnerTest {

    @Test
    void runByTag() {
        String tag = System.getProperty("karate.tag", "");
        Results results;

        if (tag == null || tag.isBlank()) {
            results = Runner.path("classpath:com/ligo/api/features").parallel(1);
        } else {
            results = Runner.path("classpath:com/ligo/api/features")
                    .tags(tag)
                    .parallel(1);
        }

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
