package com.example.demo.model;

public class Car {
    private String teamName;
    private double speedKPH;

    public Car(String teamName, double speedKPH) {
        this.teamName = teamName;
        this.speedKPH = speedKPH;
    }
    public String getTeamName() {
        return teamName;
    }
    public double getSpeedKPH() {
        return speedKPH;
    }

    public static void main(String[] args) {
       Car ferrari = new Car("Scuderia Ferrari", 350.0);
       System.out.println("Team:" + ferrari.getTeamName() + ", Speed: " + ferrari.getSpeedKPH() + " KPH");
    

    }
    
}
