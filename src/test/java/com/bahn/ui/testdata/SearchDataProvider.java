package com.bahn.ui.testdata;

import com.bahn.ui.domain.QuerySearch;
import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    private static final String VALID_ORIGIN_NAME = "Bonn";
    private static final String VALID_DESTINATION_NAME = "Frankfurt";
    private static final String VALID_DATE = "08.10.2022";
    private static final String VALID_TIME = "15:00";
    public static QuerySearch validQuery = new QuerySearch(VALID_ORIGIN_NAME,VALID_DESTINATION_NAME,VALID_DATE,VALID_TIME, true);

    @DataProvider(name = "validQueryParameters")
    public Object[] setValidQueryParameters() {
        return new Object[]{
                new QuerySearch("Aschaffenburg Hbf", "Mannheim Hbf", "12.10.2022", "12:00", true),
                new QuerySearch("Wuppertal Hbf", "Hamburg-Bergedorf", "20.08.2022", "19:30", false)};
    }

    @DataProvider(name = "emptyStationName")
    public Object[] setEmptyStationName() {
        return new Object[]{" ", "", "Франкфурт"};
    }

    @DataProvider(name = "severalPossibleStationName")
    public Object[] setSeveralPossibleStationName() {
        return new Object[]{"100.", "121Q"};
    }

    @DataProvider(name = "invalidDate")
    public Object[] setInvalidDate() {
        return new Object[]{"12 June 2022", "3.Nov.22", "00/00/00"};
    }

    @DataProvider(name = "insideTimetableDate")
    public static Object[] setInsideTimetableDate() {
        return new Object[]{"11-12-22", "#11,12,22", " 11 . 12.2022  "};
    }

    @DataProvider(name = "incorrectFormatTime")
    public Object[] setIncorrectFormatTime() {
        return new Object[]{".", "7777", "noon"};
    }
}
