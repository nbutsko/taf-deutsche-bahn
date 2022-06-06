package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.steps.SearchRouteSteps;
import com.bahn.ui.testdata.SearchDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPageTest extends AbstractTest {

    private SearchRouteSteps searchRouteSteps;
    private QuerySearch regressionQuery;

    @BeforeClass
    public void openPageAndAcceptCookies() {
        String pageLanguage = "English";
        searchRouteSteps = new SearchRouteSteps();
        searchRouteSteps.openHomePageAndAcceptCookies(pageLanguage);
    }

    @BeforeMethod
    public void openSearchForm() {
        searchRouteSteps.openSearchForm();
        regressionQuery = new SearchDataProvider().getValidQuery();
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Smoke")
    @Test(description = "Smoke - search route",
            dataProvider = "validQueryParameters",
            dataProviderClass = SearchDataProvider.class)
    public void testSearchRouteWithValidData(QuerySearch querySearch) {
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertTrue(searchRouteSteps.isAllResultsContainTheQueryStations(querySearch));
        assertTrue(searchRouteSteps.isAllResultsMatchTheQueryTime(querySearch));
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Regression")
    @Test(description = "Regression - search route with empty input station", dataProvider = "emptyStationName", dataProviderClass = SearchDataProvider.class)
    public void testEmptyInputOriginStation(String stationName) {
        regressionQuery.setOrigin(stationName);
        searchRouteSteps.fillAndSubmitSearchForm(regressionQuery);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_EMPTY_INPUT_STATION);
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Regression")
    @Test(description = "Regression - search route with different versions of the station name", dataProvider = "severalPossibleStationName", dataProviderClass = SearchDataProvider.class)
    public void testInputOriginStationWithSeveralPossibleData(String stationName) {
        regressionQuery.setOrigin(stationName);
        searchRouteSteps.fillAndSubmitSearchForm(regressionQuery);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_SEVERAL_POSSIBLE_INPUT_STATION);
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Regression")
    @Test(description = "Regression - search route with an invalid date", dataProvider = "invalidDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInvalidData(String date) {
        regressionQuery.setDate(date);
        searchRouteSteps.fillAndSubmitSearchForm(regressionQuery);

        assertEquals(searchRouteSteps.getDateErrorMessage(), searchRouteSteps.getMessageInvalidInputDate(date));
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Regression")
    @Test(description = "Regression - search route with an off-schedule date", dataProvider = "insideTimetableDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInsideTimetableData(String date) {
        regressionQuery.setDate(date);
        searchRouteSteps.fillAndSubmitSearchForm(regressionQuery);

        assertEquals(searchRouteSteps.getDateErrorMessage(), searchRouteSteps.MESSAGE_INPUT_DATE_INSIDE_THE_TIMETABLE);
    }

    @Epic("UI")
    @Feature("Search route")
    @Story("Regression")
    @Test(description = "Regression - search route with an incorrect format time", dataProvider = "incorrectFormatTime", dataProviderClass = SearchDataProvider.class)
    public void testInputTimeWithIncorrectFormatData(String time) {
        regressionQuery.setTime(time);
        searchRouteSteps.fillAndSubmitSearchForm(regressionQuery);

        assertEquals(searchRouteSteps.getTimeErrorMessage(), searchRouteSteps.getMessageNotCorrectFormatInputTime(time));
    }
}
