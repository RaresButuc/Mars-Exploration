package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.time.Duration;
import java.util.List;

public interface ConfigurationValidator {
    boolean validateConfigurationObject(Configuration configuration);

    List<Coordinate> checkAdjacentCoordinate(String map, Coordinate coordinate);

    boolean checkSymbols(List<String> symbols);

    boolean checkLandingSpots(String map, Coordinate coordinate);
}