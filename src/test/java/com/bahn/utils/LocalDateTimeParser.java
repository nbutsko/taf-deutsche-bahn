package com.bahn.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {

    public static LocalDate parseStringToLocalDate (String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
