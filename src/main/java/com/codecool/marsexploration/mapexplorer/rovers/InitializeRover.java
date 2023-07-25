package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;

import java.util.*;

public class InitializeRover {
    private final ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl();

    public MarsRover initializeRover(String map, Coordinate coordinate, int sight, HashMap<String, Coordinate> resources) {
        List<Coordinate> emptySpots = configurationValidator.checkAdjacentCoordinate(map, coordinate);

        System.out.println("Empty spots "+emptySpots);
        Random randomPosition = new Random();
        if (emptySpots.size() > 0) {
            Coordinate randomCoordinate = emptySpots.get(randomPosition.nextInt(emptySpots.size()));
            return new MarsRover(randomCoordinate, sight, resources);
        }
        return null;
    }
}