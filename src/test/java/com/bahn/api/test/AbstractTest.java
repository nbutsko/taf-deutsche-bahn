package com.bahn.api.test;

import com.bahn.api.client.CustomClient;
import com.bahn.api.utils.JourneyUtils;
import com.bahn.api.utils.StationUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AbstractTest {
    CustomClient httpClient;
    protected StationUtils stationUtils = new StationUtils();
    protected JourneyUtils journeyUtils = new JourneyUtils();

    @BeforeClass
    public void getClient() {
        httpClient = new CustomClient();
    }

    @AfterClass
    public void closeClient() {
        httpClient.closeClient();
    }
}
