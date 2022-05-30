package com.bahn.ui.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends AbstractPage {

    @FindBy(className = "overviewConnection")
    private List<WebElement> searchResultCards;

    public List<WebElement> getSearchResultCards(){
        return searchResultCards;
    }
}
