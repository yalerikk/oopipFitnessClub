package com.example.demokurs.Entity;

public class Price {
    private String type;
    private double cost;
    private String specialist;

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

    public Price (String type, double cost, String specialist){
        this.type = type;
        this.cost = cost;
        this.specialist = specialist;
    }
}
