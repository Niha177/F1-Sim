package com.example.demo.model;

public class Team {
    private String name;
    private double basePitTime;

    public Team(String name, double basePitTime) {
        this.name = name;
        this.basePitTime = basePitTime;
    }

    public String getName() {
        return name;
    }
    public double getBasePitTime() {
        return basePitTime;
    }


}
