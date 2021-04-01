package com.cmccarthy.ui;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber/report.json"},
        features = "src/test/resources/features/WikipediaTest.feature",
        glue = {"com/cmccarthy/ui", "com/cmccarthy/common"}
)
public class WikipediaRunnerTest {

}


