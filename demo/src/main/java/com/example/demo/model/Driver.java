package com.example.demo.model;

public class Driver {
    private String name;
    private int number;
    private double skillLevel;
    private String teamName;
    private int driverYear;
    public Driver(String name, String teamName, double skillLevel, int driverYear) {
        this.name = name;
        this.teamName = teamName;
        this.skillLevel = skillLevel;
        this.driverYear = driverYear;
        
    }
    public Driver(String name, int number, double skillLevel, int driverYear) {
        this.name = name;
        this.number = number;
        this.skillLevel = skillLevel;
        this.driverYear = driverYear;
    }

    public Driver(String name, int number, double skillLevel) {
        this.name = name;
        this.number = number;
        this.skillLevel = skillLevel;
    }

    public Driver(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public Driver(String name) {
        this.name = name;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int Number() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setSkillLevel(double skillLevel) {
        this.skillLevel = skillLevel;
    }
    public double getSkillLevel() {
        return skillLevel;
    }
    public String getTeam() {
        return teamName;
    }
    public void setTeam(String teamName) {
        this.teamName = teamName;
    }
    public int getDriverYear() {
        return driverYear;
    }


    public static void main(String[] args) {
        Driver lewis = new Driver("Lewis Hamilton");
        System.out.println("Driver: " + lewis.getName());

        Driver leclerc = new Driver("Charles Leclerc");
        System.out.println("Driver:" + leclerc.getName());

    }
    
}
