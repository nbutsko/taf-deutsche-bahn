package com.bahn.ui.pageobjects;

import com.bahn.utils.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BahnComPage extends AbstractPage{

    @FindBy(css = "button.js-accept-all-cookies")
    private WebElement buttonAcceptCookies;

    @FindBy(css = "a.navigation-teaser__link")
    private List<WebElement> languages;

    public BahnComPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public BahnComPage clickButtonAcceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(buttonAcceptCookies));
        buttonAcceptCookies.click();
        UtilLogger.logger.info(buttonAcceptCookies);
        return this;
    }

    public HomePage selectLanguage(String languageToSelect){
        for (WebElement language : languages) {
            if (language.getText().contains(languageToSelect)) {
                language.click();
                UtilLogger.logger.info("Click " + language.getText());
                break;
            }
        }
        return new HomePage();
    }
}
