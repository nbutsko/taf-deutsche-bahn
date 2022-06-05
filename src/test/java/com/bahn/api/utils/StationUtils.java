package com.bahn.api.utils;

import com.bahn.api.entity.Station;
import com.bahn.logger.UtilLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StationUtils {

    public List<Station> getListStations(String body) {
        List<Station> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(body);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                String id = ((JSONObject) jsonObject.get(key)).getString("id");
                String name = ((JSONObject) jsonObject.get(key)).getString("name");
                result.add(new Station(id, name));
            }
        }
        return result;
    }

    private void logResponseResults(String responseBody) {
        for (Station station : getListStations(responseBody)) {
            UtilLogger.logger.info(station.toString());
        }
    }

    @Attachment(value = "Stations list", type = "application/json", fileExtension = ".txt")
    public String getStationListAttachment(String responseBody){
        StringBuilder result = new StringBuilder();
        for (Station station : getListStations(responseBody)) {
            result.append(station.toString()).append("\n");
        }
        return String.valueOf(result);
    }

    @Step("Is response contains {0}")
    public boolean isResponseContainsStation(String stationName, String responseBody) {
        getStationListAttachment(responseBody);
        logResponseResults(responseBody);
        return getListStations(responseBody).stream()
                .map(Station::getName)
                .allMatch(s -> s.contains(stationName));
    }
}
