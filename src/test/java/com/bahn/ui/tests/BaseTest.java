package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import com.bahn.ui.steps.SearchRouteStep;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class BaseTest extends AbstractTest {
    private QuerySearch querySearch;

    @BeforeTest
    public void setQueryParameters(){
        String origin = "Aschaffenburg";
        String destination = "Frankfurt";
        LocalDate date = LocalDate.of(2022, 8, 20);
        String time = "15:00";
        boolean departureStatus = true;
        querySearch = new QuerySearch(origin, destination, date, time, departureStatus);
    }

    @Test
    public void testOpenPage(){
        String pageLanguage = "English";

        SearchRouteStep searchRouteStep = new SearchRouteStep();
        searchRouteStep.openSearchPageWithSelectedLanguage(pageLanguage);
        searchRouteStep.fillAndSubmitSearchForm(querySearch);

        Assert.assertTrue(searchRouteStep.isResultsMatchTheQuery(querySearch));
    }
}
