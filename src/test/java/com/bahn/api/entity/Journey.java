package com.bahn.api.entity;

public class Journey {

    private String from;
    private String to;
    private String date;
    private String time;
    private boolean departureStatus;

    public Journey(String from, String to, String date, String time, boolean departureStatus) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.departureStatus = departureStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        return "Journey{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", departureStatus=" + departureStatus +
                '}';
    }
}
