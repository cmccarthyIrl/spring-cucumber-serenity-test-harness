package com.cmccarthy.ui.utils;

import net.thucydides.core.steps.StepEventBus;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import net.thucydides.core.webdriver.ProvidedDriverConfiguration;
import net.thucydides.core.webdriver.stubs.WebDriverStub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager implements DriverSource {

    private final EnvironmentVariables environmentVariables;

    public DriverManager(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    @Override
    public WebDriver newDriver() {
        final ProvidedDriverConfiguration sourceConfig = new ProvidedDriverConfiguration(environmentVariables);

        try {
            if (StepEventBus.getEventBus().webdriverCallsAreSuspended()) {
                return new WebDriverStub();
            }
            switch (sourceConfig.getDriverName().toLowerCase()) {
                case "chrome":
                    final ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("window-size=1920,1080");
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("-incognito");
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.addArguments("disable-infobars");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    return new ChromeDriver(ChromeDriverService.createDefaultService(), chromeOptions);
                case "firefox":
                default:
                    throw new IllegalStateException("Unexpected value: " + sourceConfig.getDriverName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate the custom webdriver provider of type " + sourceConfig.getDriverName(), e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}