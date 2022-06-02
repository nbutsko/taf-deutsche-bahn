package com.bahn.api.entity;

public class Route {

    private String firstStation;
    private String destinationStation;
    private String departure;
    private String arrival;

    public Route(String firstStation, String destinationStation, String departure, String arrival) {
        this.firstStation = firstStation;
        this.destinationStation = destinationStation;
        this.departure = departure;
        this.arrival = arrival;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "Route{" +
                "firstStation='" + firstStation + '\'' +
                ", destStation='" + destinationStation + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                '}';
    }
}
