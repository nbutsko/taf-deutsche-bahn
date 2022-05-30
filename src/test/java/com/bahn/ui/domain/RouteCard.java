package com.bahn.ui.domain;

public class RouteCard {

    private String departureTime;
    private String arrivalTime;
    private String firstStation;
    private String destinationStation;

    public RouteCard(String departureTime, String arrivalTime, String firstStation, String destinationStation) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.firstStation = firstStation;
        this.destinationStation = destinationStation;
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

    @Override
    public String toString() {
        return "RouteCard{" +
                "depTime='" + departureTime + '\'' +
                ", arrTime='" + arrivalTime + '\'' +
                ", firstStation='" + firstStation + '\'' +
                ", destStation='" + destinationStation + '\'' +
                '}';
    }
}
