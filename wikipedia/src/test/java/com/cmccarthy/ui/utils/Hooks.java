package com.cmccarthy.ui.utils;

import com.cmccarthy.common.utils.HookUtils;
import com.cmccarthy.ui.config.WikipediaAbstractTestDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks extends WikipediaAbstractTestDefinition {

    @Autowired
    private HookUtils hookUtil;

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void afterScenario(Scenario scenario) {
        hookUtil.endOfTest(scenario);
    }

}