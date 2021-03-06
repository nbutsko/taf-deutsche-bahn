package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BahnComPage extends AbstractPage {

    @FindBy(css = "button.js-accept-all-cookies")
    private WebElement buttonAcceptCookies;

    @FindBy(css = "a.navigation-teaser__link")
    private List<WebElement> languages;

    public BahnComPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public BahnComPage clickButtonAcceptCookies() {
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(buttonAcceptCookies));
        buttonAcceptCookies.click();
        return this;
    }

    public HomePage selectLanguage(String languageToSelect) {
        for (WebElement language : languages) {
            if (language.getText().contains(languageToSelect)) {
                UtilLogger.logger.info("Select Language " + language.getText());
                language.click();
                break;
            }
        }
        return new HomePage();
    }
}
