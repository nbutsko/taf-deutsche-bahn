package com.bahn.ui.domain;

import java.time.LocalDate;

public class QuerySearch {
    private String origin;
    private String destination;
    private LocalDate date;
    private String time;
    private boolean departureStatus;

    public QuerySearch(String origin, String destination, LocalDate date, String time, boolean departureStatus) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.departureStatus = departureStatus;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDepartureStatus() {
        return departureStatus;
    }

    public void setDepartureStatus(boolean departureStatus) {
        this.departureStatus = departureStatus;
    }
}
