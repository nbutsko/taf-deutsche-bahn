package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends AbstractPage {

    public final String MESSAGE_EMPTY_INPUT_STATION = "Please fill in a stop/station.";
    public final String MESSAGE_SEVERAL_POSSIBLE_INPUT_STATION = "Your input yielded several possible stops. Please select the desired stop.";
    public final String MESSAGE_INPUT_DATE_INSIDE_THE_TIMETABLE = "Your input is not inside the timetable period between 12.12.21 and 10.12.22.";
    public String messageInvalidInputDate = "Your input \"%s\" is an invalid date.";
    public String messageNotCorrectFormatInputTime = "Your input \"%s\" is either not in the correct format, e.g. \"12:00\" or is an invalid timevalue.";

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

    @Step("Type origin station {0}")
    public SearchPage typeInputOrigin(String origin) {
        inputOrigin.clear();
        inputOrigin.sendKeys(origin);
        UtilLogger.logger.info("Type origin station " + origin);
        return this;
    }

    @Step("Type destination station {0}")
    public SearchPage typeInputDestination(String destination) {
        inputDestination.clear();
        inputDestination.sendKeys(destination);
        UtilLogger.logger.info("Type destination station " + destination);
        return this;
    }

    @Step("Type date {0}")
    public SearchPage typeDate(String date) {
        inputDate.clear();
        inputDate.sendKeys(date);
        UtilLogger.logger.info("Type date " + date);
        return this;
    }

    @Step("Type time {0}")
    public SearchPage typeTime(String time) {
        inputTime.clear();
        inputTime.sendKeys(time);
        UtilLogger.logger.info("Type time " + time);
        return this;
    }

    @Step("Departure status = {0}")
    public SearchPage selectDepartureOrArrival(boolean departureStatus) {
        if (departureStatus) {
            if (!radiobuttonDepartureArrival.get(0).isSelected()) {
                radiobuttonDepartureArrival.get(0).click();
                UtilLogger.logger.info("Select time by departure time");
            }
        } else {
            if (!radiobuttonDepartureArrival.get(1).isSelected()) {
                radiobuttonDepartureArrival.get(1).click();
                UtilLogger.logger.info("Select time by arrival time");
            }
        }
        return this;
    }

    @Step("Submit search route form")
    public SearchResultsPage clickButtonSearch() {
        buttonSearch.click();
        UtilLogger.logger.info("Click buttonSearch");
        return new SearchResultsPage();
    }

    @Step("Origin error message")
    public String getOriginErrorMessage() {
        UtilLogger.logger.info(originErrorMessage.getText());
        return originErrorMessage.getText();
    }

    @Step("Date error message")
    public String getDateErrorMessage() {
        UtilLogger.logger.info(dateErrorMessage.getText());
        return dateErrorMessage.getText();
    }

    @Step("Time error message")
    public String getTimeErrorMessage() {
        UtilLogger.logger.info(timeErrorMessage.getText());
        return timeErrorMessage.getText();
    }
}
