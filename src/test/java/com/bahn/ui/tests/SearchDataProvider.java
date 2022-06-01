package com.bahn.ui.tests;

import com.bahn.ui.domain.QuerySearch;
import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    static final String VALID_ORIGIN_NAME = "Bonn";
    static final String VALID_DESTINATION_NAME = "Frankfurt";
    static final String VALID_DATE = "08.10.2022";
    static final String VALID_TIME = "15:00";

    @DataProvider(name = "validQueryParameters")
    public Object[] setValidQueryParameters() {
        return new Object[]{
                new QuerySearch("Aschaffenburg", "Flensburg", "12.10.2022", "12:00", true),
                new QuerySearch("Frankfurt", "Bonn", "20.08.2022", "19:30", false)};
    }

    @DataProvider(name = "emptyStationName")
    public Object[] setEmptyStationName() {
        return new Object[]{" ", "", "Франкфурт"};
    }

    @DataProvider(name = "notInterpretableStationName")
    public Object[] setNotInterpretableStationName() {
        return new Object[]{".", "###", "!Q_1"};
    }

    @DataProvider(name = "severalPossibleStationName")
    public Object[] setSeveralPossibleStationName() {
        return new Object[]{"100.", "121Q"};
    }

    @DataProvider(name = "invalidDate")
    public Object[] setInvalidDate() {
        return new Object[]{"12 June 2022", "12.22", "3.Nov.22", "00/00/00"};
    }

    @DataProvider(name = "incorrectFormatDate")
    public Object[] setIncorrectFormatDate() {
        return new Object[]{".", "May", "00000"};
    }

    @DataProvider(name = "insideTimetableDate")
    public static Object[] setInsideTimetableDate() {
        return new Object[]{"11-12-22", "11/12/2022", "#11,12,22", " 11 . 12.2022  "};
    }

    @DataProvider(name = "incorrectFormatTime")
    public Object[] setIncorrectFormatTime() {
        return new Object[]{".", "7777", "hour"};
    }
}
