package com.cmccarthy.api.step;

import com.cmccarthy.api.config.WeatherAbstractTestDefinition;
import com.cmccarthy.api.model.response.LocationWeatherRootResponse;
import com.cmccarthy.api.service.WeatherService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WeatherStep extends WeatherAbstractTestDefinition {

    private final Logger logger = LoggerFactory.getLogger(WeatherStep.class);
    @Autowired
    private WeatherService weatherService;

    @Then("The weather for {word} should be returned")
    public void theWeatherForDublinShouldBeReturned(String location) {
        final SoftAssertions softAssertions = new SoftAssertions();

        final LocationWeatherRootResponse locationWeatherRootResponse = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", LocationWeatherRootResponse.class);

        softAssertions.assertThat(locationWeatherRootResponse.getName())
                .as("Expected the weather forecast to be for : " + location)
                .withFailMessage("But it was for : " + locationWeatherRootResponse.getName())
                .isEqualToIgnoringCase(location);
        softAssertions.assertAll();
    }

    @Given("The {actor} has requested the weather for {word}")
    public void theUserHasRequestedTheWeatherForDublin(Actor actor, String location) {
        logger.info("The user makes an request for the weather in : " + location);
        actor.attemptsTo(weatherService.getWeatherForLocation(actor, location));
    }
}