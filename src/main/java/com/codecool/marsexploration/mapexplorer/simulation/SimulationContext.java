package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;

public class SimulationContext {
    private int numberOfSteps;
    private int timeoutSteps;
    private MarsRover rover;
    private Coordinate spaceshipLocation;
    private String[][] map;
    private String[] monitoredResources;
    private ExplorationOutcome explorationOutcome;

    // Constructor
    public SimulationContext(int numberOfSteps, int timeoutSteps, MarsRover rover,
                             Coordinate spaceshipLocation, String[][] map,
                             String[] monitoredResources) {
        this.numberOfSteps = numberOfSteps;
        this.timeoutSteps = timeoutSteps;
        this.rover = rover;
        this.spaceshipLocation = spaceshipLocation;
        this.map = map;
        this.monitoredResources = monitoredResources;
    }

    // Getters and Setters (You can generate them automatically in most IDEs)

    public ExplorationOutcome getExplorationOutcome() {
        return explorationOutcome;
    }

    public void setExplorationOutcome(ExplorationOutcome explorationOutcome) {
        this.explorationOutcome = explorationOutcome;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public int getTimeoutSteps() {
        return timeoutSteps;
    }

    public MarsRover getRover() {
        return rover;
    }

    public Coordinate getSpaceshipLocation() {
        return spaceshipLocation;
    }

    public String[][] getMap() {
        return map;
    }

    public String[] getMonitoredResources() {
        return monitoredResources;
    }
}
