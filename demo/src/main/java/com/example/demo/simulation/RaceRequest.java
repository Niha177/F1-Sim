package com.example.demo.simulation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RaceRequest {

    @JsonProperty("driverName")
    private String driverName;

    @JsonProperty("trackName")
    private String trackName;

    @JsonProperty("team")
    private String team;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("year")
    private int year;

    @JsonProperty("pitStops")
    private int pitStops;

    @JsonProperty("tires")
    private List<String> tires;

    public RaceRequest() {}

    // getters & setters
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getPitStops() { return pitStops; }
    public void setPitStops(int pitStops) { this.pitStops = pitStops; }
    public List<String> getTires() { return tires; }
    public void setTires(List<String> tires) { this.tires = tires; }
}
