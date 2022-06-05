package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.steps.SearchRouteSteps;
import com.bahn.ui.testdata.SearchDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPageTest extends AbstractTest {

    private SearchRouteSteps searchRouteSteps;
    private QuerySearch querySearch;

    @BeforeClass
    public void openPageAndAcceptCookies() {
        String pageLanguage = "English";
        searchRouteSteps = new SearchRouteSteps();
        searchRouteSteps.openHomePageAndAcceptCookies(pageLanguage);
    }

    @BeforeMethod
    public void openSearchForm() {
        searchRouteSteps.openSearchForm();
        querySearch = SearchDataProvider.validQuery;
    }

    @Test(groups = "Smoke", description = "Smoke - search route", dataProvider = "validQueryParameters", dataProviderClass = SearchDataProvider.class)
    public void testSearchRouteWithValidData(QuerySearch querySearch) {
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertTrue(searchRouteSteps.isAllResultsContainTheQueryStations(querySearch));
        assertTrue(searchRouteSteps.isAllResultsMatchTheQueryTime(querySearch));
    }

    @Test(groups = "Regression", description = "Regression - search route with empty input station", dataProvider = "emptyStationName", dataProviderClass = SearchDataProvider.class)
    public void testEmptyInputOriginStation(String stationName) {
        querySearch.setOrigin(stationName);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_EMPTY_INPUT_STATION);
    }

    @Test(groups = "Regression", description = "Regression - search route with different versions of the station name", dataProvider = "severalPossibleStationName", dataProviderClass = SearchDataProvider.class)
    public void testInputOriginStationWithSeveralPossibleData(String stationName) {
        querySearch.setOrigin(stationName);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_SEVERAL_POSSIBLE_INPUT_STATION);
    }

    @Test(groups = "Regression", description = "Regression - search route with an invalid date", dataProvider = "invalidDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInvalidData(String date) {
        querySearch.setDate(date);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getDateErrorMessage(), searchRouteSteps.getMessageInvalidInputDate(date));
    }

    @Test(groups = "Regression", description = "Regression - search route with an off-schedule date", dataProvider = "insideTimetableDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInsideTimetableData(String date) {
        querySearch.setDate(date);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getDateErrorMessage(), searchRouteSteps.MESSAGE_INPUT_DATE_INSIDE_THE_TIMETABLE);
    }

    @Test(groups = "Regression", description = "Regression - search route with an incorrect format time", dataProvider = "incorrectFormatTime", dataProviderClass = SearchDataProvider.class)
    public void testInputTimeWithIncorrectFormatData(String time) {
        querySearch.setTime(time);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getTimeErrorMessage(), searchRouteSteps.getMessageNotCorrectFormatInputTime(time));
    }
}
