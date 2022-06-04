package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(id = "errormsg_S")
    private WebElement originErrorMessage;

    @FindBy(id = "dateErr20")
    private WebElement dateErrorMessage;

    @FindBy(id = "timeErr0")
    private WebElement timeErrorMessage;

    public SearchPage clickButtonAcceptCookiesAtSearchPage() {
        String shadowRootLocator = "//body/div[1]";
        String buttonAcceptCookiesSelector = "button.js-accept-all-cookies";
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath(shadowRootLocator))
                        .getShadowRoot()
                        .findElement(By.cssSelector(buttonAcceptCookiesSelector)))).click();
        UtilLogger.logger.info("Click buttonAcceptCookies at SearchPage");
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
        UtilLogger.logger.info("Click buttonSearch");
        return new SearchResultsPage();
    }

    public String getOriginErrorMessage() {
        UtilLogger.logger.info(originErrorMessage.getText());
        return originErrorMessage.getText();
    }

    public String getDateErrorMessage() {
        UtilLogger.logger.info(dateErrorMessage.getText());
        return dateErrorMessage.getText();
    }

    public String getTimeErrorMessage() {
        UtilLogger.logger.info(timeErrorMessage.getText());
        return timeErrorMessage.getText();
    }
}
