package com.cmccarthy.common.service;

import com.cmccarthy.common.utils.ApplicationProperties;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.RestRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestService {
    @Autowired
    ApplicationProperties applicationProperties;

    public RequestSpecification getRestRequest() {
        return RestRequests.given().header("Content-Type", "application/json").baseUri(applicationProperties.getWeatherAppUrl());
    }
}