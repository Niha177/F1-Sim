package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.simulation.RaceSimulator;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	RaceSimulator simulator = new RaceSimulator();

    @GetMapping("/simulate")
    public String simulate(
            @RequestParam(required = false) String driverName,
            @RequestParam(required = false) String trackName,
            @RequestParam(required = false) Integer year
    ) {
		 System.out.println("Driver: [" + driverName + "]");
    System.out.println("Track: [" + trackName + "]");
    System.out.println("Year: [" + year + "]");

	
        // Validate parameters manually so we can return friendly messages instead of HTTP 400
        if (driverName == null || driverName.isBlank() || trackName == null || trackName.isBlank() || year == null) {
            return "Missing required query parameters. Usage: /simulate?driverName=NAME&trackName=TRACK&year=YYYY";
        }
        int yearInt = year;

        double predictedTime;
        try {
            predictedTime = simulator.predictTimeSkill(driverName.trim(), trackName.trim(), yearInt);
        } catch (IllegalArgumentException iae) {
            // propagate a clearer message if internal checks fail
            return "Error: " + iae.getMessage();
        }

        if (predictedTime < 0) {
            return "Could not find data for the given driver, track, and year.";
        }

        return "Predicted lap time for " + driverName + " on " + trackName + " in " + yearInt + ": " + predictedTime + " seconds.";
    }

}
