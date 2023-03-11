package com.cmccarthy.api;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber/report.json"},
        features = "src/test/resources/features/WeatherTest.feature",
        glue = {"com/cmccarthy/api", "com/cmccarthy/common"},
        tags = "not @ignore"
)
public class WeatherRunnerTest {
}
