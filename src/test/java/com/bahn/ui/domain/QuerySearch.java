package com.bahn.ui.domain;

public class QuerySearch {
    private String origin;
    private String destination;
    private String date;
    private String time;
    private boolean departureStatus;

    public QuerySearch(String origin, String destination, String date, String time, boolean departureStatus) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "QuerySearch{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", departureStatus=" + departureStatus +
                '}';
    }
}
