package com.bahn.ui.pageobjects;

import com.bahn.utils.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private String homePageUrl;

    @FindBy(css = "div.language-selection a")
    private WebElement languageLink;

    @FindBy(css = "li.display-on-log-out a.nav__link--arrow")
    private WebElement buttonLogin;

    @FindBy(css = "a.toggleTabBtn")
    private WebElement buttonOpenInformation;

    @FindBy(css = "input.btn--secondary")
    private WebElement buttonFurtherOptions;

    public HomePage openPage() {
        driver.get(homePageUrl);
        return this;
    }

    public void setHomePageUrl(){
        homePageUrl = languageLink.getAttribute("href");
    }

    public LogInPage clickButtonLogin(){
        buttonLogin.click();
        return new LogInPage();
    }

    public SearchPage openSearchForm(){
        buttonOpenInformation.click();
        buttonFurtherOptions.click();
        UtilLogger.logger.info("Click buttonFurtherOptions");
        return new SearchPage();
    }
}
