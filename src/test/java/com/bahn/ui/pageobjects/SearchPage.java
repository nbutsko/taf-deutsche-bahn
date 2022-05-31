package com.bahn.ui.pageobjects;

import com.bahn.utils.UtilLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(className = "from")
    private WebElement inputOrigin;

    @FindBy(className = "to")
    private WebElement inputDestination;

    @FindBy(css = "input#REQ0JourneyDate")
    private WebElement inputDate;

    @FindBy(css = "input#REQ0JourneyTime")
    private WebElement inputTime;

    @FindBy(css = "div#dateDepArr0 label")
    private List<WebElement> radiobuttonDepartureArrival;

    @FindBy(css = "input#searchConnectionButton")
    private WebElement buttonSearch;

    public SearchPage clickButtonAcceptCookiesAtSearchPage() {
        /*try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String clickShadowButtonScript = "return document.body.querySelector('div').shadowRoot.querySelector('.js-accept-all-cookies')";
            WebElement buttonAcceptCookies = (WebElement)executor.executeScript(clickShadowButtonScript);
            buttonAcceptCookies.click();
        } catch (JavascriptException e) {
            UtilLogger.logger.warn(e.getMessage());
        }*/
        String shadowRootLocator = "//body/div[1]";
        String buttonAcceptCookiesSelector = "button.js-accept-all-cookies";
        try {
            WebElement buttonAcceptCookies = driver.findElement(By.xpath(shadowRootLocator))
                    .getShadowRoot()
                    .findElement(By.cssSelector(buttonAcceptCookiesSelector));
            buttonAcceptCookies.click();
        } catch (NoSuchShadowRootException ignored) {
            UtilLogger.logger.info("No shadow root elements.");
        }
        return this;
    }

    public SearchPage typeInputOrigin(String origin) {
        inputOrigin.clear();
        inputOrigin.sendKeys(origin);
        return this;
    }

    public SearchPage typeInputDestination(String destination) {
        inputDestination.clear();
        inputDestination.sendKeys(destination);
        return this;
    }

    public SearchPage typeDate(String date) {
        inputDate.clear();
        inputDate.sendKeys(date);
        return this;
    }

    public SearchPage typeTime(String time) {
        inputTime.clear();
        inputTime.sendKeys(time);
        return this;
    }

    public SearchPage selectDepartureOrArrival(boolean departureStatus) {
        if (departureStatus) {
            if (!radiobuttonDepartureArrival.get(0).isSelected()) {
                radiobuttonDepartureArrival.get(0).click();
            }
        } else {
            if (!radiobuttonDepartureArrival.get(1).isSelected()) {
                radiobuttonDepartureArrival.get(1).click();
            }
        }
        return this;
    }

    public SearchResultsPage clickButtonSearch() {
        buttonSearch.click();
        return new SearchResultsPage();
    }
}
