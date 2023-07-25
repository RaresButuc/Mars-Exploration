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
    public boolean validateConfigurationObject(Configuration configuration) {
        List<String> mapLoader = new MapLoaderImpl().readAllLines(configuration.map());
        String map = String.join("", mapLoader);
        System.out.println(mapLoader);
        return configuration.steps() > 0
                && checkLandingSpots(map, configuration.landingSpot())
                && checkAdjacentCoordinate(map, configuration.landingSpot())
                && !configuration.map().isEmpty()
                && checkSymbols(configuration.symbols());
    }

    private boolean checkAdjacentCoordinate(String map, Coordinate coordinate) {
        int x = coordinate.X();
        int y = coordinate.Y();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0 && checkLandingSpots(map, new Coordinate(x + i, y + j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSymbols(List<String> symbols) {
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

    //    public boolean checkLandingSpots(String map, Coordinate coordinate) {
//        int x = coordinate.X();
//        int y = coordinate.Y();
//
////        return Objects.equals(map[x][y], " ");
//        int sqrtOfMapSize = (int) Math.sqrt(map.length());
//
//        String spot = map.split("")[x * sqrtOfMapSize + y]; //(x+1) daca incep de la 1 1
//        return Objects.equals(spot, " ");
//    }
    public boolean checkLandingSpots(String map, Coordinate coordinate) {
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
}
