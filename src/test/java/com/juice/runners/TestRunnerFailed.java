package com.juice.runners;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"@target/failedrerun.txt"},
        plugin = {"pretty",
                "summary",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "timeline:target/cucumber-timeline"
        },
        monochrome = false,
        dryRun = false,
        publish = true
)
public class TestRunnerFailed {
}


