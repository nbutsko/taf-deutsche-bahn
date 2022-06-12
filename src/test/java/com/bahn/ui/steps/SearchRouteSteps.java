package com.bahn.ui.steps;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.domain.RouteCard;
import com.bahn.ui.pageobjects.BahnComPage;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.SearchPage;
import com.bahn.ui.pageobjects.SearchResultsPage;
import com.bahn.logger.UtilLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchRouteSteps extends SearchPage {

    private HomePage homePage;
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @Step("Open home page in {0} and accept all cookies")
    public void openHomePageAndAcceptCookies(String pageLanguage) {
        homePage = new BahnComPage().openPage()
                .clickButtonAcceptCookies()
                .selectLanguage(pageLanguage);
        homePage.setHomePageUrl();
        homePage.openSearchForm()
                .clickButtonAcceptCookiesAtSearchPage();
    }

    @Step("Open search route form")
    public void openSearchForm() {
        searchPage = homePage.openPage()
                .openSearchForm();
    }

    @Step("Fill out the route search form")
    public void fillAndSubmitSearchForm(QuerySearch querySearch) {
        searchResultsPage = searchPage
                .typeInputOrigin(querySearch.getOrigin())
                .typeInputDestination(querySearch.getDestination())
                .typeDate(querySearch.getDate())
                .typeTime(querySearch.getTime())
                .selectDepartureOrArrival(querySearch.isDepartureStatus())
                .clickButtonSearch();
        UtilLogger.logger.info(querySearch);
    }

    private List<RouteCard> getListOfRouteCards() {
        String depTimeSelector = "div.connectionTimeSoll span.timeDep";
        String arrTimeSelector = "div.connectionTimeSoll span.timeArr";
        String firstStationSelector = "div.station.first";
        String destinationStationSelector = "div.stationDest";
        String date = searchResultsPage.getDepartureDate();
        List<RouteCard> routeCardList = new ArrayList<>();
        for (WebElement routeCard : searchResultsPage.getSearchResultCards()) {
            String depTime = routeCard.findElement(By.cssSelector(depTimeSelector)).getText();
            String arrTime = routeCard.findElement(By.cssSelector(arrTimeSelector)).getText();
            String firstStation = routeCard.findElement(By.cssSelector(firstStationSelector)).getText();
            String destinationStation = routeCard.findElement(By.cssSelector(destinationStationSelector)).getText();
            routeCardList.add(new RouteCard(firstStation, destinationStation,date, depTime, arrTime));
        }
        return routeCardList;
    }

    private void logSearchResults() {
        for (RouteCard routeCard : getListOfRouteCards()) {
            UtilLogger.logger.info(routeCard.toString());
        }
    }

    @Step("Is route results contain stations {querySearch.origin}, {querySearch.destination}")
    public boolean isAllResultsContainTheQueryStations(QuerySearch querySearch) {
        logSearchResults();
        boolean isContainOrigin = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getFirstStation().contains(querySearch.getOrigin()));
        boolean isContainDestination = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getDestinationStation().contains(querySearch.getDestination()));
        return isContainOrigin & isContainDestination;
    }

    @Step("Is route results match {querySearch.time} {querySearch.date}, departureStatus = {querySearch.departureStatus}")
    public boolean isAllResultsMatchTheQueryTime(QuerySearch querySearch) {
        boolean result = false;
        LocalTime queryTime = LocalTime.parse(querySearch.getTime());
        if (querySearch.isDepartureStatus()) {
            result = getListOfRouteCards().stream()
                    .allMatch(routeCard -> LocalTime.parse(routeCard.getDepartureTime()).compareTo(queryTime.minusMinutes(20)) > 0);
        } else {
            result = getListOfRouteCards().stream()
                    .allMatch(routeCard -> LocalTime.parse(routeCard.getArrivalTime()).compareTo(queryTime.plusMinutes(20)) < 0);
        }
        return result;
    }

    public String getMessageInvalidInputDate(String inputDate){
        return String.format(searchPage.messageInvalidInputDate, inputDate);
    }

    public String getMessageNotCorrectFormatInputTime(String inputTime){
        return String.format(searchPage.messageNotCorrectFormatInputTime, inputTime);
    }
}
