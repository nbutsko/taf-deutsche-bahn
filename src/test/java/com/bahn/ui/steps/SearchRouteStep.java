package com.bahn.ui.steps;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.domain.RouteCard;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.SearchPage;
import com.bahn.ui.pageobjects.SearchResultsPage;
import com.bahn.utils.UtilLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchRouteStep {

    HomePage homePage;
    SearchPage searchPage;
    SearchResultsPage searchResultsPage;

    public void openSearchPageWithSelectedLanguage(String language) {
        homePage = new HomePage().openPage()
                .clickButtonAcceptCookies()
                .changeLanguage("English")
                .clickButtonAcceptCookies();
    }

    public void openSearchForm() {
        searchPage = new HomePage().openPage()
                .openSearchForm();
    }

    public void fillAndSubmitSearchForm(QuerySearch querySearch) {
        searchResultsPage = searchPage.typeInputOrigin(querySearch.getOrigin())
                .typeInputDestination(querySearch.getDestination())
                .selectDate(querySearch.getDate())
                .typeTime(querySearch.getTime())
                .selectDepartureOrArrival(querySearch.isDepartureStatus())
                .clickButtonSearch();
    }

    private List<RouteCard> getListOfRouteCards() {
        String depTimeSelector = "div.timeDep";
        String arrTimeSelector = "div.timeArr";
        String firstStationSelector = "div.station.first";
        String destinationStationSelector = "div.stationDest";
        List<RouteCard> routeCardList = new ArrayList<>();
        for (WebElement routeCard : searchResultsPage.getSearchResultCards()) {
            String depTime = routeCard.findElement(By.cssSelector(depTimeSelector)).getText();
            String arrTime = routeCard.findElement(By.cssSelector(arrTimeSelector)).getText().substring(3);
            String firstStation = routeCard.findElement(By.cssSelector(firstStationSelector)).getText();
            String destinationStation = routeCard.findElement(By.cssSelector(destinationStationSelector)).getText();
            routeCardList.add(new RouteCard(depTime, arrTime, firstStation, destinationStation));
        }
        return routeCardList;
    }

    private void logSearchResults() {
        for (RouteCard routeCard : getListOfRouteCards()) {
            UtilLogger.logger.info(routeCard.toString());
        }
    }

    public boolean isAllResultsContainTheQueryStations(QuerySearch querySearch) {
        logSearchResults();
        boolean isContainOrigin = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getFirstStation().contains(querySearch.getOrigin()));
        boolean isContainDestination = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getDestinationStation().contains(querySearch.getDestination()));
        return isContainOrigin & isContainDestination;
    }

    public boolean isAllResultsMatchTheQueryTime(QuerySearch querySearch){
        boolean result = false;
        LocalTime queryTime = LocalTime.parse(querySearch.getTime());
        if (querySearch.isDepartureStatus()) {
            result = getListOfRouteCards().stream()
                    .allMatch(routeCard -> LocalTime.parse(routeCard.getDepartureTime()).compareTo(queryTime.minusMinutes(10)) > 0);
        } else {
            result = getListOfRouteCards().stream()
                    .allMatch(routeCard -> LocalTime.parse(routeCard.getArrivalTime()).compareTo(queryTime.plusMinutes(10)) < 0);
        }
        return result;
    }
}
