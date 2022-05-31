package com.bahn.ui.pageobjects;

import com.bahn.ui.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

    protected final static String BASE_URL = "https://www.bahn.com/";
    protected WebDriver driver;

    public final static int WAIT_TIMEOUT_SECONDS = 10;

    public AbstractPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}