package com.example.demo.model;

import java.util.List;
import java.util.ArrayList;
import com.example.demo.model.Tire;

public class Strategy {
    private int pitStopNumber;
    private int lapNumber;
    private Tire tireChoice;

    private double tirewearLevel;
    //private List<Tire> tires;

    public Strategy(int pitStopNumber, int lapNumber, Tire tireChoice, double tirewearLevel) {
            this.pitStopNumber = pitStopNumber;
            this.lapNumber = lapNumber;
            this.tireChoice = tireChoice;
            this.tirewearLevel = tirewearLevel;
    }
    public Strategy(int numLaps, Tire tireChoice, double tire) {
         this.lapNumber = numLaps;
         this.tireChoice = tireChoice;
        this.tirewearLevel = tire;
        // Placeholder constructor for future use
    }
    //public Strategy(List<Tire> tires) {
    //    this.tires = tires;
    //}

    public int getPitStopNumber() {
        return pitStopNumber;
    }
    public void setPitStopNumber(int pitStopNumber) {
        this.pitStopNumber = pitStopNumber;
    }
    public int getLapNumber() {
        return lapNumber;
    }
    // Convenience alias for readability: callers in RaceSimulator use getLaps()
    public int getLaps() {
        return getLapNumber();
    }
    public Tire getTire() {
        return getTireChoice();
    }
    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber; 
    }
    public Tire getTireChoice() {
        return tireChoice;
    }
    public void setTireChoice(Tire tireChoice) {
        this.tireChoice = tireChoice;   
    }
    public double getTirewearLevel() {
        return tirewearLevel;
    }
    public void setTirewearLevel(int tirewearLevel) {
        this.tirewearLevel = tirewearLevel;
    }
   // public List<Tire> getTires() {
        // Placeholder method for future implementation
    //    return tires;
    //}
    /* 
    public void addTire(Tire tire, List<Tire> list) { //if 1 pitstop, 2 tires, if 2 pitstops, 3 tires (add these conditions in strat)
        list.add(tire);
        this.tires = list;
        //implement frontend input for tire choice and pit stop number, then use that to determine the tires to add to the strategy
        
    }
    */
    
        
    /*public List<Tire> correctTires(int pitstops) {
        
        List<Tire> tires = null;

        if(pitstops == 1) {
           tires = Tire.tireOnePitStop(tireChoice, tireChoice);
        } else if(pitstops == 2) {
            tires = Tire.tireTwoPitStop(tireChoice, tireChoice, tireChoice);
        } else if(pitstops == 3) {
            tires = Tire.tireThreePitStop(tireChoice, tireChoice, tireChoice, tireChoice);
        } else if(pitstops == 4) {
            tires = Tire.tireFourPitStop(tireChoice, tireChoice, tireChoice, tireChoice, tireChoice);
        } else if(pitstops == 5) {
            tires = Tire.tireFivePitStop(tireChoice, tireChoice, tireChoice, tireChoice, tireChoice, tireChoice);
        }

        return tires;
    }
        */

    public static List<Strategy> setPlan(int pitStops, Tracks track, List<Tire> tires) {
        List<Strategy> stint = new ArrayList<>();

        if (pitStops > 5) {
            throw new IllegalArgumentException("Pit stops cannot exceed 5");
        }
        if(tires.size() != pitStops + 1) {
            throw new IllegalArgumentException("Number of tires must be equal to pit stops + 1");
        }   
        // Placeholder method for future implementation

        //pitstop + 1 tires
        //remainder = laps % stopnumber + 1
        int numTires = pitStops + 1;
        int remainder = track.getLaps() % numTires;

        for(int x = 0; x < numTires; x++) {
            double tirewear = tires.get(x).getDegradationRate();
            int lapNumber = track.getLaps() / (pitStops + 1);

            while(remainder > 0) {
                lapNumber++;
                remainder--;
            }
            
             
            Strategy strategy = new Strategy(lapNumber, tires.get(x), tirewear);
            stint.add(strategy);
        }
       // Strategy strategy = new Strategy(0, null, 0);

        return stint;
    }
    
    
}
