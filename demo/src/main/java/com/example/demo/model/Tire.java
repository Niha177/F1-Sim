package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Tire {
    private String type;
    private double degradationRate;
    private static List<Tire> tires;

    public Tire(String type, double degradationRate) {
        this.type = type;
        this.degradationRate = degradationRate;
    }
    public Tire() {
        List<Tire> tire = new ArrayList<>();
        this.tires = tire;
    }

    public String getType() {
        return type;
    }
    public double getDegradationRate() {
        return degradationRate; 
    }
    public static Tire addSoftTire() {
        return new Tire("Soft", 0.12);
    }
    public static Tire addMediumTire() {
        return new Tire("Medium", 0.20);
    }
    public static Tire addHardTire() {
        return new Tire("Hard", 0.32);
    }
    public static List<Tire> getTires() {
        return tires;
    }

      public void addTire(Tire tire) { // add a tire to this Tire's tire list (strategy)
        if (tires == null) {
            tires = new ArrayList<>();
        }
        tires.add(tire);
        //implement frontend input for tire choice and pit stop number, then use that to determine the tires to add to the strategy
        
    }
    public static Tire creatTires(String type) {
        switch (type.toLowerCase()) {
            case "soft":
                return addSoftTire();
            case "medium":
                return addMediumTire();
            case "hard":
                return addHardTire();
            default:
                throw new IllegalArgumentException("Invalid tire type: " + type);
        }
    }

    public static List<Tire> tireOnePitStop(Tire tireChoice, Tire tireChoice2) {
         List<Tire> tires = new ArrayList<>();
            tires.add(tireChoice);
            tires.add(tireChoice2);
        return tires;
    }
     public static List<Tire> tireTwoPitStop(Tire tireChoice, Tire tireChoice2, Tire tireChoice3) {
         List<Tire> tires = new ArrayList<>();
            tires.add(tireChoice);
            tires.add(tireChoice2);
            tires.add(tireChoice3);
        return tires;
    }
     public static List<Tire> tireThreePitStop(Tire tireChoice, Tire tireChoice2, Tire tireChoice3, Tire tireChoice4) {
         List<Tire> tires = new ArrayList<>();
            tires.add(tireChoice);
            tires.add(tireChoice2);
            tires.add(tireChoice3);
            tires.add(tireChoice4);
        return tires;
    }
     public static List<Tire> tireFourPitStop(Tire tireChoice, Tire tireChoice2, Tire tireChoice3, Tire tireChoice4, Tire tireChoice5) {
         List<Tire> tires = new ArrayList<>();
            tires.add(tireChoice);
            tires.add(tireChoice2);
            tires.add(tireChoice3);
            tires.add(tireChoice4);
            tires.add(tireChoice5);
        return tires;
    }
     public static List<Tire> tireFivePitStop(Tire tireChoice, Tire tireChoice2, Tire tireChoice3, 
        Tire tireChoice4, Tire tireChoice5, Tire tireChoice6) {
         List<Tire> tires = new ArrayList<>();
            tires.add(tireChoice);
            tires.add(tireChoice2);
            tires.add(tireChoice3);
            tires.add(tireChoice4);
            tires.add(tireChoice5);
            tires.add(tireChoice6);
        return tires;
    }
    public static void main(String[] args) {
        Tire t = new Tire();
        List<Tire> myTires = new ArrayList<>();
        myTires.add(addHardTire());
        myTires.add(addMediumTire());
        myTires.add(addSoftTire());

       
    }
}
