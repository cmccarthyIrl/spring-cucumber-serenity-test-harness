package com.cmccarthy.api.service;

import com.cmccarthy.common.service.RestService;
import com.cmccarthy.common.service.StepDefinitionDataManager;
import com.cmccarthy.common.utils.ApplicationProperties;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

    private Logger logger = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RestService restService;
    @Autowired
    private StepDefinitionDataManager stepDefinitionDataManager;
    @Autowired
    private ApplicationProperties applicationProperties;


    public Performable getWeatherForLocation(Actor actor, String location) {


        actor.whoCan(CallAnApi.at(applicationProperties.getWeatherAppUrl()));

//        actor.attemptsTo(
//                Get.resource("/data/2.5/weather").with(request ->
//                        request.queryParam("appid", "0a1b11f110d4b6cd43181d23d724cb94")
//                                .queryParam("q", location)
//                )
//        );

//        System.out.println("SerenityRest.lastResponse().prettyPrint() = " + SerenityRest.lastResponse().prettyPrint());

//        final LocationWeatherRootResponse weatherRootResponse = SerenityRest.lastResponse()
//                .jsonPath()
//                .getObject("", LocationWeatherRootResponse.class);

//        stepDefinitionDataManager.addToStoredObjectMap("class", weatherRootResponse);
        return Task.where("{0} opens the Wikipedia home page",
                Get.resource("/data/2.5/weather").with(request ->
                        request.queryParam("appid", "0a1b11f110d4b6cd43181d23d724cb94")
                                .queryParam("q", location)
                ));
    }
}
