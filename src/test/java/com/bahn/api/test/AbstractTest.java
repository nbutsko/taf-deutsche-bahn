package com.bahn.api.test;

import com.bahn.api.client.CustomClient;
import com.bahn.api.utils.JourneyUtils;
import com.bahn.api.utils.StationUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractTest {
    protected CustomClient httpClient;
    protected StationUtils stationUtils = new StationUtils();
    protected JourneyUtils journeyUtils = new JourneyUtils();

    @BeforeTest
    public void getClient() {
        httpClient = new CustomClient();
    }

    @AfterTest
    public void closeClient() {
        httpClient.closeClient();
    }
}
