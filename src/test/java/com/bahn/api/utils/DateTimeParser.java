package com.bahn.api.utils;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    static String getStringZonedDateTime(String date, String time) {
        String dateTime = date + " " + time + " +02:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
        return String.valueOf(ZonedDateTime.parse(dateTime, formatter));
    }

    static LocalTime getLocalTimeFromStringDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, formatter);
        return zonedDateTime.toLocalTime();
    }
}
