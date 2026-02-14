package com.example.demo.model;

public class Tire {
    private String type;
    private double degradationRate;
    public Tire(String type, double degradationRate) {
        this.type = type;
        this.degradationRate = degradationRate;
    }
    public String getType() {
        return type;
    }
    public double getDegradationRate() {
        return degradationRate; 
    }
    
}
