package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.steps.SearchRouteSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchPageTest extends AbstractTest {

    private SearchRouteSteps searchRouteSteps;

    @BeforeTest
    public void openPageAndAcceptCookies(){
        String pageLanguage = "English";
        searchRouteSteps = new SearchRouteSteps();
        searchRouteSteps.openHomePageAndAcceptCookies(pageLanguage);
    }

    @BeforeMethod
    public void openSearchForm(){
        searchRouteSteps.openSearchForm();
    }

    @Test(dataProvider = "validQueryParameters", dataProviderClass = SearchDataProvider.class)
    public void testSearchRouteWithValidData(QuerySearch querySearch){
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertTrue(searchRouteSteps.isAllResultsContainTheQueryStations(querySearch));
        assertTrue(searchRouteSteps.isAllResultsMatchTheQueryTime(querySearch));
    }

    @Test(dataProvider = "emptyStationName", dataProviderClass = SearchDataProvider.class)
    public void testEmptyInputOriginStation(String stationName){
        QuerySearch querySearch = new QuerySearch(stationName, SearchDataProvider.VALID_DESTINATION_NAME, SearchDataProvider.VALID_DATE, SearchDataProvider.VALID_TIME, true);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_EMPTY_INPUT_STATION);
    }

    @Test(dataProvider = "notInterpretableStationName", dataProviderClass = SearchDataProvider.class)
    public void testInputOriginStationWithNotInterpretableData(String stationName){
        QuerySearch querySearch = new QuerySearch(stationName, SearchDataProvider.VALID_DESTINATION_NAME, SearchDataProvider.VALID_DATE, SearchDataProvider.VALID_TIME, true);
        String expectedMessage = String.format(searchRouteSteps.messageNotInterpretableInputStation, stationName);

        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), expectedMessage);
    }

    @Test(dataProvider = "severalPossibleStationName", dataProviderClass = SearchDataProvider.class)
    public void testInputOriginStationWithSeveralPossibleData(String stationName){
        QuerySearch querySearch = new QuerySearch(stationName, SearchDataProvider.VALID_DESTINATION_NAME, SearchDataProvider.VALID_DATE, SearchDataProvider.VALID_TIME, true);

        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getOriginErrorMessage(), searchRouteSteps.MESSAGE_SEVERAL_POSSIBLE_INPUT_STATION);
    }

    @Test(dataProvider = "invalidDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInvalidData(String date){
        QuerySearch querySearch = new QuerySearch(SearchDataProvider.VALID_ORIGIN_NAME, SearchDataProvider.VALID_DESTINATION_NAME, date, SearchDataProvider.VALID_TIME, true);
        String expectedMessage = String.format(searchRouteSteps.messageInvalidInputDate, date);

        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getDateErrorMessage(), expectedMessage);
    }

    @Test(dataProvider = "incorrectFormatDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithIncorrectFormatData(String date){
        QuerySearch querySearch = new QuerySearch(SearchDataProvider.VALID_ORIGIN_NAME, SearchDataProvider.VALID_DESTINATION_NAME, date, SearchDataProvider.VALID_TIME, true);
        String expectedMessage = String.format(searchRouteSteps.messageNotCorrectFormatInputDate, date);

        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getDateErrorMessage(), expectedMessage);
    }

    @Test(dataProvider = "insideTimetableDate", dataProviderClass = SearchDataProvider.class)
    public void testInputDateWithInsideTimetableData(String date){
        QuerySearch querySearch = new QuerySearch(SearchDataProvider.VALID_ORIGIN_NAME, SearchDataProvider.VALID_DESTINATION_NAME, date, SearchDataProvider.VALID_TIME, true);
        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getDateErrorMessage(), searchRouteSteps.MESSAGE_INPUT_DATE_INSIDE_THE_TIMETABLE);
    }

    @Test(dataProvider = "incorrectFormatTime", dataProviderClass = SearchDataProvider.class)
    public void testInputTimeWithIncorrectFormatData(String time){
        QuerySearch querySearch = new QuerySearch(SearchDataProvider.VALID_ORIGIN_NAME, SearchDataProvider.VALID_DESTINATION_NAME, SearchDataProvider.VALID_DATE, time, true);
        String expectedMessage = String.format(searchRouteSteps.messageNotCorrectFormatInputTime, time);

        searchRouteSteps.fillAndSubmitSearchForm(querySearch);

        assertEquals(searchRouteSteps.getTimeErrorMessage(), expectedMessage);
    }
}