package com.ligo.web.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/ligo/web/features",
        glue = "com.ligo.web.stepdefinitions",
        tags = "@AUTOMATION_WEB",
        plugin = {"pretty", "html:target/cucumber-reports/web.html"},
        monochrome = true
)
public class RunCucumberTest {
}
