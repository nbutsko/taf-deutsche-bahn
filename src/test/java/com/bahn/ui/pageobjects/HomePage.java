package com.bahn.ui.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(css = "button.js-accept-all-cookies")
    private WebElement buttonAcceptCookies;

    @FindBy(css = "button.language-selection__button")
    private WebElement buttonChangeLanguage;

    @FindBy(css = "li.language-selection__list-item")
    private List<WebElement> languages;

    @FindBy(css = "a.toggleTabBtn")
    private WebElement buttonOpenInformation;

    @FindBy(css = "input.btn--secondary")
    private WebElement buttonFurtherOptions;

    public HomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public HomePage clickButtonAcceptCookies() {
        buttonAcceptCookies.click();
        return this;
    }

    public HomePage changeLanguage(String languageToSelect) {
        buttonChangeLanguage.click();
        for (WebElement language : languages) {
            if (language.getText().contains(languageToSelect)){
                language.click();
                break;
            }
        }
        return this;
    }

    public SearchPage openSearchForm(){
        buttonOpenInformation.click();
        buttonFurtherOptions.click();
        return new SearchPage();
    }
}
