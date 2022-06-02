package com.bahn.api.test;

import com.bahn.api.client.CustomClient;
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
        return new Object[]{"Frankfurt", "Bonn"};
    }

    @Test(dataProvider = "validStations")
    public void testGetStationsRequest(String station) {
        String url = "https://v5.db.transport.rest/stations";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("query", station));
        //httpClient = new CustomClient();
        httpClient.sendGet(url, params);

        String body = httpClient.getBody();

        assertEquals(httpClient.getStatusCode(), 200);
        assertTrue(stationUtils.isResponseContainsStation(station, body));
    }
}
