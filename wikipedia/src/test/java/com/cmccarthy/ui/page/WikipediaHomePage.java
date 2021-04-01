package com.cmccarthy.ui.page;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://wikipedia.org")
public class WikipediaHomePage extends PageObject {

    public static final Target COMMONS_LOGO = Target.the("article heading").locatedBy("//a[contains(@href,'commons.wikimedia.org')]");

    public static Performable theWikipediaHomePage() {
        return Task.where("{0} opens the Wikipedia home page",
                Open.browserOn().the(WikipediaHomePage.class));
    }

}
