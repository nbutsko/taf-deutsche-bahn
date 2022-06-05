package com.bahn.ui.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends AbstractPage {

    @FindBy(id = "tp_overview_headline_date")
    private WebElement departureDate;

    @FindBy(className = "overviewConnection")
    private List<WebElement> searchResultCards;

    public String getDepartureDate(){
        return departureDate.getText();
    }

    public List<WebElement> getSearchResultCards(){
        return searchResultCards;
    }
}
