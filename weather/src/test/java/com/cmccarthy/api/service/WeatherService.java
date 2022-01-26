package com.cmccarthy.api.service;

import com.cmccarthy.api.model.response.LocationWeatherRootResponse;
import com.cmccarthy.common.service.RestService;
import com.cmccarthy.common.service.StepDefinitionDataManager;
import com.cmccarthy.common.utils.ApplicationProperties;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

    @Autowired
    private RestService restService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    @Autowired
    private ApplicationProperties applicationProperties;


    public void getWeatherForLocation(Actor actor, String location) {
        final Response weatherResponse = restService.getRestRequest()
                .queryParam("appid", "0a1b11f110d4b6cd43181d23d724cb94")
                .queryParam("q", location)
                .get("/data/2.5/weather");

//        System.out.println("SerenityRest.lastResponse().prettyPrint() = " + weatherResponse.prettyPrint());

        final LocationWeatherRootResponse weatherRootResponse = weatherResponse
                .jsonPath()
                .getObject("", LocationWeatherRootResponse.class);

        stepDefinitionDataManager.addToStoredObjectMap("class", weatherRootResponse);
    }
}
