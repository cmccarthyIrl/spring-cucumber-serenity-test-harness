package com.cmccarthy.ui.step;

import com.cmccarthy.ui.page.WikipediaCommonPage;
import com.cmccarthy.ui.page.WikipediaHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;

public class WikipediaPageSteps {

    @Given("{actor} opened the Wikipedia Homepage")
    public void theActorOpenedTheWikipediaHomepage(Actor actor) {
        actor.wasAbleTo(WikipediaHomePage.theWikipediaHomePage());
    }

    @And("{actor} clicked on the Common link")
    public void theUserClickedOnTheCommonLink(Actor actor) {
        actor.attemptsTo(Click.on(WikipediaHomePage.COMMONS_LOGO));
    }

    @Then("{actor} should be on the Common page")
    public void theUserShouldBeOnTheCommonPage(Actor actor) {
        actor.attemptsTo(Ensure.that(WikipediaCommonPage.COMMON_PAGE_TITLE).isDisplayed());
    }
}
