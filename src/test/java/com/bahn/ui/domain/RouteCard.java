package com.bahn.ui.domain;

public class RouteCard {

    private String firstStation;
    private String destinationStation;
    private String date;
    private String departureTime;
    private String arrivalTime;

    public RouteCard(String firstStation, String destinationStation, String date, String departureTime, String arrivalTime) {
        this.firstStation = firstStation;
        this.destinationStation = destinationStation;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "RouteCard{" +
                "firstStation='" + firstStation + '\'' +
                ", destStation='" + destinationStation + '\'' +
                ", date='" + date + '\'' +
                ", depTime='" + departureTime + '\'' +
                ", arrTime='" + arrivalTime + '\'' +
                '}';
    }
}
