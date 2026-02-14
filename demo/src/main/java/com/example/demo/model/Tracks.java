package com.example.demo.model;

public class Tracks {
    private String name;
    private double baseLapTime;
    private int laps;

    public Tracks(String name, double baseLapTime, int laps) {
        this.name = name;
        this.baseLapTime = baseLapTime;
        this.laps = laps;
    }

    public String getName() {
        return name;
    }

    public double getBaseLapTime() {
        return baseLapTime;
    }

    public int getLaps() {
        return laps;
    }
}
