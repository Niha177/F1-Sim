package com.example.demo.io;

import com.example.demo.model.Driver;
import com.example.demo.model.Team;
import com.example.demo.model.Tracks;
import com.example.demo.model.Tire;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {


    
    public List<Driver> readDrivers() 
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/drivers.csv");

        if (inputStream == null) {
            System.err.println("Could not find resource: data/drivers.csv on the classpath");
            return new ArrayList<>();
        }

       




        List<Driver> drivers = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)) ) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (line == null || line.trim().isEmpty()) {
                    continue; // skip blank lines
                }
                String[] values = line.split(",");
                if (values.length < 4) {
                    System.err.println("Skipping malformed driver line (expected 4 columns): " + line);
                    continue;
                }
                String name = values[0].trim();
                String team = values[1].trim();
                double skill;
                int year;
                try {
                    skill = Double.parseDouble(values[2].trim());
                    year = Integer.parseInt(values[3].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping driver with invalid number format: " + line + " (" + nfe.getMessage() + ")");
                    continue;
                }
                drivers.add(new Driver(name, team, skill, year));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drivers;
    
}
public List<Team> readTeam() {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/team.csv");

    if (inputStream == null) {
        System.err.println("Could not find resource: data/team.csv on the classpath");
        return new ArrayList<>();
    }


        List<Team> teams = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                if (values.length < 2) {
                    System.err.println("Skipping malformed team line (expected 2 columns): " + line);
                    continue;
                }
                String name = values[0].trim();
                double basePitTime;
                try {
                    basePitTime = Double.parseDouble(values[1].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping team with invalid number format: " + line + " (" + nfe.getMessage() + ")");
                    continue;
                }
                teams.add(new Team(name, basePitTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public List<Tracks> readTracks() {

         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/tracks.csv");

         if (inputStream == null) {
             System.err.println("Could not find resource: data/tracks.csv on the classpath");
             return new ArrayList<>();
         }

        List<Tracks> tracks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                if (values.length < 3) {
                    System.err.println("Skipping malformed track line (expected 3 columns): " + line);
                    continue;
                }
                String name = values[0].trim();
                double baseLapTime;
                int laps;
                try {
                    baseLapTime = Double.parseDouble(values[1].trim());
                    laps = Integer.parseInt(values[2].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping track with invalid number format: " + line + " (" + nfe.getMessage() + ")");
                    continue;
                }
                tracks.add(new Tracks(name, baseLapTime, laps));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public List<Tire> readTires() {

         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/tires.csv");

        if (inputStream == null) {
            System.err.println("Could not find resource: data/tires.csv on the classpath");
            return new ArrayList<>();
        }




        List<Tire> tires = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",");
                if (values.length < 2) {
                    System.err.println("Skipping malformed tire line (expected 2 columns): " + line);
                    continue;
                }
                String type = values[0].trim();
                double degradationRate;
                try {
                    degradationRate = Double.parseDouble(values[1].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping tire with invalid number format: " + line + " (" + nfe.getMessage() + ")");
                    continue;
                }
                tires.add(new Tire(type, degradationRate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tires;
    }


public static void main(String[] args) {
    CsvReader drive = new CsvReader();

        List<Driver> drivers = drive.readDrivers();
        for (Driver d : drivers) {
            System.out.println(d.getName() + " - " + d.getTeam() + " - " + d.getSkillLevel() + " - " + d.getDriverYear());
        }
        System.out.println("-------------------");


         CsvReader team = new CsvReader();
        List<Team> teams = team.readTeam();
        for (Team t : teams) {
            System.out.println(t.getName() + " - " + t.getBasePitTime());
    }
     System.out.println("-------------------");

            CsvReader track = new CsvReader();
    List<Tracks> tracks = track.readTracks();
        for (Tracks tr : tracks) {
            System.out.println(tr.getName() + " - " + tr.getBaseLapTime() + " - " + tr.getLaps());
    }

        System.out.println("-------------------");

            CsvReader tire = new CsvReader();
    List<Tire> tires = tire.readTires();
        for (Tire ti : tires) {
            System.out.println(ti.getType() + " - " + ti.getDegradationRate());
    }
}

}