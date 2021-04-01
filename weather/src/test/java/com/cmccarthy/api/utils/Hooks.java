package com.cmccarthy.api.utils;

import com.cmccarthy.api.config.WeatherAbstractTestDefinition;
import com.cmccarthy.common.utils.HookUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks extends WeatherAbstractTestDefinition {

    @Autowired
    private HookUtils hookUtil;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void afterScenario(Scenario scenario) {
        hookUtil.endOfTest(scenario);
    }
}