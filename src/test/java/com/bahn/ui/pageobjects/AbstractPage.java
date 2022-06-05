package com.bahn.ui.pageobjects;

import com.bahn.ui.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    protected final static String BASE_URL = "https://www.bahn.com/";
    protected WebDriver driver;
    protected final static int WAIT_TIMEOUT_SECONDS = 30;

    public AbstractPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWebDriverWait(WebDriver webDriver){
        return new WebDriverWait(webDriver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }
}
