package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Strategy;
import com.example.demo.model.Tire;
import com.example.demo.model.Tracks;
import com.example.demo.simulation.RaceRequest;
import com.example.demo.simulation.RaceSimulator;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	RaceSimulator simulator = new RaceSimulator();

    @GetMapping("/simulate")
    public String simulate(@RequestParam String driverName, @RequestParam String trackName, @RequestParam Integer year) {
		// System.out.println("Driver: [" + driverName + "]");
    //System.out.println("Track: [" + trackName + "]");
    //System.out.println("Year: [" + year + "]");

	
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

    @GetMapping("/simulater")
public void simulate(@RequestParam String tires) {

    List<String> tireList = new ArrayList<>();

    String[] selectedTires = tires.split(",");

    for (String tire : selectedTires) {
        tireList.add(tire);
    }

    System.out.println(tireList);
}

@PostMapping("/simulater")
    public String simulater(@RequestBody RaceRequest request) {

       simulator.setTireChoices(request.getTires());

    double result = simulator.predictAllFactors(
            request.getDriverName(),
            request.getTrackName(),
            request.getTeam(),
            request.getWeather(),
            request.getYear(),
            request.getPitStops()
    );

    return "Race time: " + result;

        //return "Received tires successfully!";
    }





}
