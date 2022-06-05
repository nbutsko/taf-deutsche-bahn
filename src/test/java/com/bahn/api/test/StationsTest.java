package com.bahn.api.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StationsTest extends AbstractTest {

    @DataProvider(name = "validStations")
    public Object[] setValidStationName() {
        return new Object[]{"Stuttgart", "Bonn"};
    }

    @Epic("API")
    @Feature("GET Stations")
    @Story("Smoke")
    @Test(description = "Test API GET station by name", dataProvider = "validStations")
    public void testGetStationsRequest(String station) {
        String url = "https://v5.db.transport.rest/stations";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("query", station));
        httpClient.sendGet(url, params);

        String body = httpClient.getBody();

        assertEquals(httpClient.getStatusCode(), 200);
        assertTrue(stationUtils.isResponseContainsStation(station, body));
    }
}
