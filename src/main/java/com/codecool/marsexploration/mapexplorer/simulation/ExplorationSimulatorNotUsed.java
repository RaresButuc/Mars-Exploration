package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExplorationSimulatorNotUsed {
    private final InitializeRover initializeRover = new InitializeRover();
    private final ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl();

    public ExplorationOutcome runSimulation(Configuration configuration, int sight) {
        Coordinate landingSpot = configuration.landingSpot();
        boolean isValidLandingSpot =configurationValidator.checkLandingSpots(landingSpot, configuration);
        HashMap<String, List<Coordinate>> resources = getResources(configuration);

        // Generate the context
        MarsRover marsRover = initializeRover.initializeRover(landingSpot, sight, resources, configuration);
        if (marsRover == null || !isValidLandingSpot) {
            // Rover could not be initialized, return ERROR
            return ExplorationOutcome.ERROR;
        }

        int steps = configuration.steps();
        for (int step = 1; step <= steps; step++) {
            // Run ordered simulation steps for each iteration of the loop
            ExplorationOutcome outcome = runSimulationStep(marsRover,configuration);
            if (outcome == ExplorationOutcome.TIMEOUT || outcome == ExplorationOutcome.COLONIZABLE) {
                // Outcome found, terminate the simulation
                System.out.println(outcome.name());
                return outcome;
            }
        }

        // Simulation completed without finding the desired outcome within the steps limit
        return ExplorationOutcome.TIMEOUT;
    }

    private ExplorationOutcome runSimulationStep(MarsRover rover, Configuration configuration) {
        Coordinate currentPosition = rover.getCurrentPosition();
        List<Coordinate> emptySpots = configurationValidator.checkAdjacentCoordinate(currentPosition,configuration);
        if(emptySpots.size() == 8){
            return ExplorationOutcome.TIMEOUT;
        }
        return ExplorationOutcome.COLONIZABLE;
    }

    public  HashMap<String, List<Coordinate>> getResources(Configuration configuration) {
        List<String> mapLoader = new MapLoaderImpl().readAllLines(configuration.map());
        String map= String.join("", mapLoader);
       // System.out.println("map= "+map);
        HashMap<String, List<Coordinate>> resourcesMap = new HashMap<>();
        int rows = mapLoader.size();
        int cols = mapLoader.get(0).length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = map.charAt(i * (cols) + j);
                if (symbol != ' ' && symbol != '\n') {
                    String symbolKey = Character.toString(symbol);
                    Coordinate coordinate = new Coordinate(j, i);
                    resourcesMap.computeIfAbsent(symbolKey, k -> new ArrayList<>()).add(coordinate);
                }
            }
        }
        for (String symbolKey : resourcesMap.keySet()) {
            List<Coordinate> coordinates = resourcesMap.get(symbolKey);
            System.out.println("Symbol: " + symbolKey);
            for (Coordinate coordinate : coordinates) {
                System.out.println("   Coordinate: " + coordinate);
            }
        }

        return resourcesMap;
    }
}
