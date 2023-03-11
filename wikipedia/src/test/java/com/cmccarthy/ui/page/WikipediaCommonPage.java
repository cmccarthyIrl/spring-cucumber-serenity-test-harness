package com.cmccarthy.ui.page;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

public class WikipediaCommonPage extends PageObject {

    public static final Target COMMON_PAGE_TITLE = Target.the("common page heading").locatedBy("//div[@class='mainpage-welcome-sitename']");
}

