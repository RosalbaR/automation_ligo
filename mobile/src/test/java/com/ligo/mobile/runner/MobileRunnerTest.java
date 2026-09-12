package com.ligo.mobile.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/ligo/mobile/features",
        glue = "com.ligo.mobile.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports/mobile.html"},
        monochrome = true
)
public class MobileRunnerTest {
}
