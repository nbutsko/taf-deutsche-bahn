package com.bahn.ui.steps;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.domain.RouteCard;
import com.bahn.ui.pageobjects.BahnComPage;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.SearchPage;
import com.bahn.ui.pageobjects.SearchResultsPage;
import com.bahn.logger.UtilLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchRouteSteps extends SearchPage {

    private HomePage homePage;
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    public final String MESSAGE_EMPTY_INPUT_STATION = "Please fill in a stop/station.";
    public final String MESSAGE_SEVERAL_POSSIBLE_INPUT_STATION = "Your input yielded several possible stops. Please select the desired stop.";
    public final String MESSAGE_INPUT_DATE_INSIDE_THE_TIMETABLE = "Your input is not inside the timetable period between 12.12.21 and 10.12.22.";
    public String messageInvalidInputDate = "Your input \"%s\" is an invalid date.";
    public String messageNotCorrectFormatInputTime = "Your input \"%s\" is either not in the correct format, e.g. \"12:00\" or is an invalid timevalue.";

    public void openHomePageAndAcceptCookies(String language) {
        homePage = new BahnComPage().openPage()
                .clickButtonAcceptCookies()
                .selectLanguage(language);
        homePage.setHomePageUrl();
        homePage.openSearchForm()
                .clickButtonAcceptCookiesAtSearchPage();
    }

    public void openSearchForm() {
        searchPage = homePage.openPage()
                .openSearchForm();
    }

    public void fillAndSubmitSearchForm(QuerySearch querySearch) {
        searchResultsPage = searchPage
                .typeInputOrigin(querySearch.getOrigin())
                .typeInputDestination(querySearch.getDestination())
                .typeDate(querySearch.getDate())
                .typeTime(querySearch.getTime())
                .selectDepartureOrArrival(querySearch.isDepartureStatus())
                .clickButtonSearch();
    }

    private List<RouteCard> getListOfRouteCards() {
        String depTimeSelector = "div.timeDep";
        String arrTimeSelector = "div.timeArr";
        String firstStationSelector = "div.station.first";
        String destinationStationSelector = "div.stationDest";
        String date = searchResultsPage.getDepartureDate();
        List<RouteCard> routeCardList = new ArrayList<>();
        for (WebElement routeCard : searchResultsPage.getSearchResultCards()) {
            String depTime = routeCard.findElement(By.cssSelector(depTimeSelector)).getText();
            String arrTime = routeCard.findElement(By.cssSelector(arrTimeSelector)).getText().substring(3);
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

    public boolean isAllResultsContainTheQueryStations(QuerySearch querySearch) {
        logSearchResults();
        boolean isContainOrigin = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getFirstStation().contains(querySearch.getOrigin()));
        boolean isContainDestination = getListOfRouteCards().stream()
                .allMatch(routeCard -> routeCard.getDestinationStation().contains(querySearch.getDestination()));
        return isContainOrigin & isContainDestination;
    }

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
}
