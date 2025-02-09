package com.example.demokurs.Entity;

public class Timetable {
    private String date;
    private String time;
    private String client;
    private String type;
    private double cost;
    private String specialist;

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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public Timetable(String date, String time, String client,
                     String type, double cost, String specialist){
        this.date = date;
        this.time = time;
        this.client = client;
        this.type = type;
        this.cost = cost;
        this.specialist = specialist;
    }
}
