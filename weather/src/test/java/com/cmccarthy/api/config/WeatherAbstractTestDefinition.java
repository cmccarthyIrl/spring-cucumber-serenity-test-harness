package com.cmccarthy.api.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {WeatherContextConfiguration.class})
public class WeatherAbstractTestDefinition {

}