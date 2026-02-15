package com.example.demo.simulation;
import com.example.demo.model.Driver;
import com.example.demo.model.Strategy;
import static com.example.demo.model.Strategy.setPlan;
import com.example.demo.model.Tracks;
import com.example.demo.model.Team;
import com.example.demo.model.Tire;
import static com.example.demo.model.Tire.addSoftTire;
import static com.example.demo.model.Tire.addMediumTire;
import static com.example.demo.model.Tire.addHardTire;
import com.example.demo.io.CsvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class RaceSimulator {
    private List<Driver> drivers;
    private List<Team> teams;
    private List<Tracks> tracks;
    private List<Tire> tires;

    private List<String> tireChoices;

    //private Map<String, Map<Integer, Driver>> driverYearMap;
/* 
    private void driverYearMap() {

       driverYearMap = new HashMap<>();
       for (Driver d : drivers) {
        String name = d.getName();
        int year = d.getDriverYear();

        driverYearMap.computeIfAbsent(name, k -> new HashMap<>()).put(year, d);

       }
    }
    */


    public RaceSimulator() {
        CsvReader loader = new CsvReader();

         
         this.drivers = loader.readDrivers();
         this.teams = loader.readTeam();
         this.tracks = loader.readTracks();
         this.tires = loader.readTires();

       // driverYearMap();
    }

    // Small getters to allow external checks (used by the controller for diagnostics)
    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }
    public List<String> getTireChoices() {
        return tireChoices;
    }
    public void setTireChoices(List<String> tiresRecieved) {
        // assign the incoming list reference directly (the JSON->List produced by
        // Spring will typically be an ArrayList). Add a small log so we can
        // verify at runtime that the simulator received the exact object.
        if (tiresRecieved == null) {
            this.tireChoices = new ArrayList<>();
            System.out.println("RaceSimulator: set empty tireChoices (null received)");
        } else {
            this.tireChoices = tiresRecieved;
            System.out.println("RaceSimulator: tireChoices set to -> " + this.tireChoices
                + " (class: " + this.tireChoices.getClass().getName() + ")");
        }
    }
    // ----------------------------------------------------------
    public double predictTimeSkill(String driverName, String trackName, int year) {
        Driver selectedDriver = null;
        Tracks selectedTrack = null;

        boolean flagdriver = false;
        boolean flagtrack = false;
        for (Driver d : drivers) {
            if((d.getName().equalsIgnoreCase(driverName)) && d.getDriverYear() == year) {
                selectedDriver = d;
                flagdriver = true;
            }
    
        }
        for (Tracks t : tracks) {
            if((t.getName().equalsIgnoreCase(trackName))) {
                selectedTrack = t;
                flagtrack = true;
            }
            
        }
        if ((flagdriver == true && flagtrack == true)) {
            return timeSkillFormula(selectedDriver, selectedTrack, year);
            //System.out.println("Driver or Track not found.");
        } else {
            return -1;
        }
        
        
    }
    
    public static double timeSkillFormula(Driver driver, Tracks track, int year) {
        
        if (driver.getDriverYear() != year) {
        throw new IllegalArgumentException("Driver data does not match requested year");
    }
        double predictTime = driver.getSkillLevel() * track.getBaseLapTime();
        return predictTime;
    }
    //----------------------------------------------------------
    /* 
    public void testCsvAccess() {
    for (Driver d : drivers) {
        System.out.println(d.getName() + " | Skill: " + d.getSkillLevel());
    }
}
    */
public List<Strategy> getStintDefault(Tracks track, Team team, Strategy tireChoice, int pitStopNumber) {
    List<Strategy> stint = new ArrayList<>();

    for (int x = 0; x < pitStopNumber; x++) {
        int lapNumber = track.getLaps() / (pitStopNumber + 1) * (x + 1);
        //need to switch tire type somehow, initialize the strategy with intent of tires to use
        //make new method in strategy to do this?


        //tint.add(new Strategy(track, team, tire));
    }


return stint;
}
//public double predictAllFactors(String driverName, String trackName, String team, String weather, int year) {
    // default: soft tires, 1 pit stop
    //return predictAllFactors(driverName, trackName, team, weather, year, "soft", 1);
    //return 0;
//}



public double predictAllFactors(String driverName, String trackName, String team, 
    String weather, int year, int pitStops) {

        //tires = tireChoices;

    //Tire t = new Tire();

   
    
    double laptime = 0;

    double weatherMod = 1;

    Driver selectedDriver = null;
    Tracks selectedTrack = null;
    Team selectedTeam = null;
    Tire selectedTire = null;

    boolean flagdriver = false;
    boolean flagtrack = false;
    boolean flagteam = false;
   // boolean flagtire = false;

    if (weather != null) {
        if (weather.equalsIgnoreCase("dry")) {
            weatherMod = 1;
        } else if (weather.equalsIgnoreCase("damp")) {
            weatherMod = 1.065;
        } else if (weather.equalsIgnoreCase("wet")) {
            weatherMod = 1.15;
        } else if (weather.equalsIgnoreCase("heavy rain")) {
            weatherMod = 1.25;
        }
    }

    for (Driver d : drivers) {
        if (d.getName().equalsIgnoreCase(driverName) && d.getDriverYear() == year) {
            selectedDriver = d;
            flagdriver = true;
        }
    }

    for (Tracks t : tracks) {
        if (t.getName().equalsIgnoreCase(trackName)) {
            selectedTrack = t;
            flagtrack = true;
        }
    }

    for (Team te : teams) {
        if(te.getName().equalsIgnoreCase(team)) {
            selectedTeam = te;
            flagteam = true;
        }
    }
/*
    if (tireType != null) {
        switch (tireType.toLowerCase()) {
            case "soft":
                selectedTire = addSoftTire();
                break;
            case "medium":
                selectedTire = addMediumTire();
                break;
            case "hard":
                selectedTire = addHardTire();
                break;
            default:
                selectedTire = addSoftTire();
        }
        flagtire = true;
    }
        */

    if (flagdriver && flagteam && flagtrack) {
        if (selectedDriver.getDriverYear() != year) {
            throw new IllegalArgumentException("Driver data does not match requested year");
        }

        // prepare a list of tire names for setPlan; if the simulator hasn't
        // received any tire choices from the frontend, fall back to a default
        // list (all "medium") of length pitStops + 1 so setPlan does not NPE.
        List<String> planTireNames = this.tireChoices;
        

        List<Strategy> strat = setPlan(pitStops, selectedTrack, planTireNames);
        

        for (int x = 0; x < strat.size(); x++) {
            double numLaps = strat.get(x).getLaps();
            double baseLapTime = selectedTrack.getBaseLapTime();

            double stintTime =  (numLaps * (baseLapTime * selectedDriver.getSkillLevel() * weatherMod)
                    + strat.get(x).getTireChoice().getDegradationRate() * ((numLaps * (numLaps + 1)) / 2));
            laptime += stintTime;
        }

        int stops = strat.size() - 1;
        laptime += selectedTeam.getBasePitTime() * stops;
    }

    return laptime;
}






public double racePositions(String trackName, int year) {
    return 0;
}

    public static void main(String[] args) {
    RaceSimulator simulator = new RaceSimulator();
    //simulator.testCsvAccess(); 
    System.out.println(simulator.predictTimeSkill("Max Verstappen", "Monaco", 2018));
   System.out.println(simulator.predictTimeSkill("Max Verstappen", "Monaco", 2025));

   //System.out.println(tireChoices.toString());
   RaceSimulator sim = new RaceSimulator();
   

    System.out.println(simulator.predictAllFactors("Max Verstappen",
     "Monaco", "Red Bull", "wet", 2025, 2));

   



    //simulator.predictTimeSkill("Lewis Hamilton", "Monza");
}

    
}
