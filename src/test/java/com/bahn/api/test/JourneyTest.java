package com.bahn.api.test;

import com.bahn.api.entity.JourneyRequest;
import com.bahn.api.entity.Station;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.NameValuePair;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JourneyTest extends AbstractTest {

    private List<Station> stations;

    @DataProvider(name = "journeys")
    public Object[] setJourneys() {
        return new Object[]{
                new JourneyRequest("Aschaffenburg", "Mannheim", "12.10.2022", "12:00:00", true),
                new JourneyRequest("Wuppertal", "Hamburg-Bergedorf", "20.08.2022", "19:30:00", false)};
    }

    @BeforeMethod
    public void getListOfStations(){
        String url = "https://v5.db.transport.rest/stations";
        httpClient.sendGet(url, new ArrayList<>());
        stations = stationUtils.getListStations(httpClient.getBody());
    }

    @Epic("API")
    @Feature("GET Journeys")
    @Story("Smoke")
    @Test(description = "Test API GET journey with parameters", dataProvider = "journeys")
    public void testGetJourneysRequest(JourneyRequest journey) {
        String url = "https://v5.db.transport.rest/journeys";
        List<NameValuePair> params = journeyUtils.getJourneyParameters(journey, stations);

        httpClient.sendGet(url, params);
        assertEquals(httpClient.getStatusCode(), 200);

        String body = httpClient.getBody();
        assertTrue(journeyUtils.isResponseContainJourneyStations(journey, body));
        assertTrue(journeyUtils.isTimeInResponseMatchJourneyTime(journey, body));
    }
}
