package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigurationValidatorImpl implements ConfigurationValidator {

    @Override
    public boolean validateConfigurationObject(Configuration mapConfiguration) {
        return mapConfiguration.steps() > 0
                && checkLandingSpots(mapConfiguration.landingSpot(), mapConfiguration)
                && checkAdjacentCoordinate(mapConfiguration.landingSpot(), mapConfiguration).size() > 0
                && !mapConfiguration.map().isEmpty()
                && checkSymbols(mapConfiguration.symbols());
    }

    @Override
    public List<Coordinate> checkAdjacentCoordinate(Coordinate coordinate, Configuration mapConfiguration) {
        List<Coordinate> adjCoordinates = new ArrayList<>();
        int x = coordinate.X();
        System.out.println("x: " + x);
        int y = coordinate.Y();
        System.out.println("y: " + y);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinate coordinateToAdd = new Coordinate(x + i, y + j);
                if (checkLandingSpots(coordinateToAdd, mapConfiguration)) {
                    adjCoordinates.add(coordinateToAdd);
                }
            }
        }
        return adjCoordinates;
    }

    @Override
    public boolean checkSymbols(List<String> symbols) {
        List<String> allSymbols = List.of("#", "&", "*", "%");
        if (symbols.size() == 0) {
            return false;
        }
        for (String symbol : symbols) {
            if (!allSymbols.contains(symbol)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkLandingSpots(Coordinate coordinate, Configuration mapConfiguration) {
        String map = convertConfigurationIntoMap(mapConfiguration);
        int x = coordinate.X();
        int y = coordinate.Y();
        int sqrtOfMapSize = (int) Math.sqrt(map.length());
        char[][] mapArray = new char[sqrtOfMapSize][sqrtOfMapSize];

        // Convert the map string into a 2D array
        int index = 0;
        for (int i = 0; i < sqrtOfMapSize; i++) {
            for (int j = 0; j < sqrtOfMapSize; j++) {
                mapArray[i][j] = map.charAt(index++);
            }
        }
        // Check if the spot is valid
        if (x >= 0 && x < sqrtOfMapSize && y >= 0 && y < sqrtOfMapSize) {
            return mapArray[x][y] == ' ';
        }
        return false;
    }

    public String convertConfigurationIntoMap(Configuration mapConfiguration){
        List<String> mapLoader = new MapLoaderImpl().readAllLines(mapConfiguration.map());
        return String.join("", mapLoader);
    }
}