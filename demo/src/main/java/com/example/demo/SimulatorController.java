/*

package com.example.demo;



import org.springframework.web.bind.annotation.*;
import com.example.demo.simulation.RaceSimulator;

@RestController

public class SimulatorController {
    RaceSimulator simulator = new RaceSimulator();

    @GetMapping("/simulate")
    public String simulate(@RequestParam String driverName, @RequestParam String trackName, @RequestParam int year) {
        double predictedTime = simulator.predictTimeSkill(driverName, trackName, year);
        if (predictedTime < 0) {
            return "Could not find data for the given driver, track, and year.";
        }
        return "Predicted lap time for " + driverName + " on " + trackName + " in " + year + ": " + predictedTime + " seconds.";
    }


    
}
    */
