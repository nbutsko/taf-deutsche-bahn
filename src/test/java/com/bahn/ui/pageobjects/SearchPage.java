package com.bahn.ui.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(className = "from")
    private WebElement inputOrigin;

    @FindBy(className = "to")
    private WebElement inputDestination;

    @FindBy(css = "a#callink0")
    private WebElement buttonCalendar;

    @FindBy(css = "span#callink0_heading_months_gt_1")
    private WebElement buttonNextMonth;

    @FindBy(css = "tbody#callink0_tbody_0 td.enabled")
    private List<WebElement> daysOfMonth;

    @FindBy(css = "input#REQ0JourneyTime")
    private WebElement inputTime;

    @FindBy(css = "div#dateDepArr0 label")
    private List<WebElement> radiobuttonDepartureArrival;

    @FindBy(css = "input#searchConnectionButton")
    private WebElement buttonSearch;

    public SearchPage typeInputOrigin(String origin) {
        inputOrigin.sendKeys(origin);
        return this;
    }

    public SearchPage typeInputDestination(String destination) {
        inputDestination.sendKeys(destination);
        return this;
    }

    public SearchPage selectDate(LocalDate date) {
        buttonCalendar.click();
        int countOfMonthsBetweenTodayAndDate = (int) ChronoUnit.MONTHS.between(LocalDate.now(), date);
        for (int i = 0; i <= countOfMonthsBetweenTodayAndDate; i++) {
            buttonNextMonth.click();
        }
        for (WebElement dayOfSelectedMonth : daysOfMonth) {
            if (dayOfSelectedMonth.getText().equals(String.valueOf(date.getDayOfMonth()))) {
                dayOfSelectedMonth.click();
                break;
            }
        }
        return this;
    }

    public SearchPage typeTime(String time) {
        inputTime.clear();
        inputTime.sendKeys(time);
        return this;
    }

    public SearchPage selectDepartureOrArrival(boolean departureStatus) {
        if (departureStatus) {
            if (!radiobuttonDepartureArrival.get(0).isSelected()){
                radiobuttonDepartureArrival.get(0).click();
            }
        } else {
            if (!radiobuttonDepartureArrival.get(1).isSelected()){
                radiobuttonDepartureArrival.get(1).click();
            }
        }
        return this;
    }

    public SearchResultsPage clickButtonSearch(){
        buttonSearch.click();
        return new SearchResultsPage();
    }
}
