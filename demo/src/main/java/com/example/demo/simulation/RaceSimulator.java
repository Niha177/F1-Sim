package com.example.demo.simulation;
import com.example.demo.model.Driver;
import com.example.demo.model.Tracks;
import com.example.demo.model.Team;
import com.example.demo.model.Tire;
import com.example.demo.io.CsvReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceSimulator {
    private List<Driver> drivers;
    private List<Team> teams;
    private List<Tracks> tracks;
    private List<Tire> tires;

    private Map<String, Map<Integer, Driver>> driverYearMap;
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

         // assign loaded data to fields so they are available to methods
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
public double predictAllFactors(String driverName, String trackName, String team, String weather, int year) {
    int laptime = 0;
    



    //take into account car performance, tire degradation, weather conditions, team
    return 0;
    /*WEATHER:
    Dry: no effect
    damp: 1.065
    wet: 1.15
    heavy rain: 1.25




    lapTime *= driver.getSkill();                 // driver skill
    lapTime *= team.getCarPerformance(track);    // car/team strength
    lapTime *= driver.getTrackModifier(track);   // track preference
    lapTime += tireModel.getDegradation(lap);    // tire wear
    lapTime += pitModel.getPitTimeIfPitLap(lap);// pit stops
    lapTime *= weather.getModifier();            // weather
    lapTime += Math.random() * 0.2;             // small randomness
    */

}

public double racePositions(String trackName, int year) {
    return 0;
}

    public static void main(String[] args) {
    RaceSimulator simulator = new RaceSimulator();
    //simulator.testCsvAccess(); 
    System.out.println(simulator.predictTimeSkill("Max Verstappen", "Monaco", 2018));
   System.out.println(simulator.predictTimeSkill("Max Verstappen", "Monaco", 2025));



    //simulator.predictTimeSkill("Lewis Hamilton", "Monza");
}
}
