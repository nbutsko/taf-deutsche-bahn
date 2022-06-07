package com.bahn.api.utils;

import com.bahn.api.entity.JourneyRequest;
import com.bahn.api.entity.Route;
import com.bahn.api.entity.Station;
import com.bahn.logger.UtilLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JourneyUtils {

    public List<NameValuePair> getJourneyParameters(JourneyRequest journey, List<Station> stations) {
        List<NameValuePair> params = new ArrayList<>();
        String depStationId = getStationIdByName(stations, journey.getFrom());
        String arrStationId = getStationIdByName(stations, journey.getTo());
        params.add(new BasicNameValuePair("from", depStationId));
        params.add(new BasicNameValuePair("to", arrStationId));
        if (journey.isDepartureStatus()) {
            params.add(new BasicNameValuePair("departure", DateTimeParser.getStringZonedDateTime(journey.getDate(), journey.getTime())));
        } else {
            params.add(new BasicNameValuePair("arrival", DateTimeParser.getStringZonedDateTime(journey.getDate(), journey.getTime())));
        }
        params.add(new BasicNameValuePair("results", "3"));
        return params;
    }

    private String getStationIdByName(List<Station> stationList, String stationName) {
        String resultId = "";
        for (Station station : stationList) {
            if (station.getName().contains(stationName)) {
                UtilLogger.logger.info(station);
                resultId = station.getId();
                break;
            }
        }
        return resultId;
    }

    public List<Route> getListJourneys(String responseBody) {
        List<Route> result = new ArrayList<>();
        JSONArray journeysArray = new JSONObject(responseBody).getJSONArray("journeys");
        for (Object journey : journeysArray) {
            JSONArray legsArray = ((JSONObject) journey).getJSONArray("legs");
            String depStation = ((JSONObject) legsArray.get(0)).getJSONObject("origin").getString("name");
            String depTime = ((JSONObject) legsArray.get(0)).getString("departure");
            String arrStation = ((JSONObject) legsArray.get(legsArray.length() - 1)).getJSONObject("destination").getString("name");
            String arrTime = ((JSONObject) legsArray.get(legsArray.length() - 1)).getString("arrival");
            result.add(new Route(depStation, arrStation, depTime, arrTime));
        }
        return result;
    }

    private void logResponseResults(String responseBody) {
        for (Route route : getListJourneys(responseBody)) {
            UtilLogger.logger.info(route.toString());
        }
    }

    @Attachment(value = "Journeys list", type = "application/json", fileExtension = ".txt")
    public String getJourneysListAttachment(String responseBody){
        StringBuilder result = new StringBuilder();
        for (Route route : getListJourneys(responseBody)) {
            result.append(route.toString()).append("\n");
        }
        return String.valueOf(result);
    }

    @Step("Is response contains {0}")
    public boolean isResponseContainJourneyStations(JourneyRequest journey, String responseBody) {
        logResponseResults(responseBody);
        getJourneysListAttachment(responseBody);
        boolean isContainFrom = getListJourneys(responseBody).stream()
                .allMatch(route -> route.getFirstStation().contains(journey.getFrom()));
        boolean isContainTo = getListJourneys(responseBody).stream()
                .allMatch(route -> route.getDestinationStation().contains(journey.getTo()));
        return isContainFrom & isContainTo;
    }

    public boolean isTimeInResponseMatchJourneyTime(JourneyRequest journey, String responseBody) {
        boolean result = false;
        LocalTime journeyTime = LocalTime.parse(journey.getTime());
        if (journey.isDepartureStatus()) {
            result = getListJourneys(responseBody).stream()
                    .map(Route::getDeparture)
                    .map(DateTimeParser::getLocalTimeFromStringDateTime)
                    .allMatch(depTime -> depTime.compareTo(journeyTime.minusMinutes(20)) > 0);
        } else {
            result = getListJourneys(responseBody).stream()
                    .map(Route::getArrival)
                    .map(DateTimeParser::getLocalTimeFromStringDateTime)
                    .allMatch(arrTime -> arrTime.compareTo(journeyTime.plusMinutes(20)) < 0);}
        return result;
    }
}
