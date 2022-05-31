package com.bahn.ui.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private String homePageUrl;

    @FindBy(css = "div.language-selection a")
    private WebElement languageLink;

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

    public SearchPage openSearchForm(){
        buttonOpenInformation.click();
        buttonFurtherOptions.click();
        return new SearchPage();
    }
}
