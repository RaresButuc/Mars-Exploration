package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExplorationSimulator {
    private final InitializeRover initializeRover = new InitializeRover();
    private final ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl();

    public ExplorationOutcome runSimulation(Configuration configuration) {
        Coordinate landingSpot = configuration.landingSpot();
        boolean isValidLandingSpot =configurationValidator.checkLandingSpots(landingSpot, configuration);
        int sight = configuration.steps();
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
            System.out.println("intra in for");
            ExplorationOutcome outcome = runSimulationStep(marsRover,configuration);
            System.out.println(outcome);
            if (outcome == ExplorationOutcome.TIMEOUT || outcome == ExplorationOutcome.COLONIZABLE) {
                System.out.println("intra in if");
                // Outcome found, terminate the simulation
                System.out.println("outcome.name().length()");
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

        //System.out.println(currentPosition);
        // Implement the logic for a single simulation step here.
        // You can move the rover, update its sight, check for resources, etc.
        // Return ExplorationOutcome.COLONIZABLE if the rover finds a desired outcome, otherwise, return ExplorationOutcome.ERROR.
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
       // for (String symbolKey : resourcesMap.keySet()) {
           // List<Coordinate> coordinates = resourcesMap.get(symbolKey);
            //System.out.println("Symbol: " + symbolKey);
           // for (Coordinate coordinate : coordinates) {
               // System.out.println("   Coordinate: " + coordinate);
           // }
       // }

        return resourcesMap;
    }
}
