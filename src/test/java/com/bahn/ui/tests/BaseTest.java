package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.steps.SearchRouteStep;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BaseTest extends AbstractTest {

    SearchRouteStep searchRouteStep;

    @DataProvider(name = "queryParameters")
    public static Object[][] setQueryParameters() {
        return new Object[][]{
                {new QuerySearch("Aschaffenburg", "Flensburg", "12.10.2022", "12:00", true)},
                {new QuerySearch("Frankfurt", "Bonn", "20.08.2022", "19:30", false)}};
    }

    @BeforeTest
    public void openPageAndAcceptCookies(){
        String pageLanguage = "English";
        searchRouteStep = new SearchRouteStep();
        searchRouteStep.openSearchPageWithSelectedLanguage(pageLanguage);
    }

    @Test(dataProvider = "queryParameters")
    public void testSearchRoute(QuerySearch querySearch){
        searchRouteStep.openSearchForm();
        searchRouteStep.fillAndSubmitSearchForm(querySearch);

        assertTrue(searchRouteStep.isAllResultsContainTheQueryStations(querySearch));
        assertTrue(searchRouteStep.isAllResultsMatchTheQueryTime(querySearch));
    }
}
