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
                && checkAdjacentCoordinate(map, configuration.landingSpot()).size() > 0
                && !configuration.map().isEmpty()
                && checkSymbols(configuration.symbols());
    }

    @Override
    public List<Coordinate> checkAdjacentCoordinate(String map, Coordinate coordinate) {
        List<Coordinate> adjCoordinates = new ArrayList<>();
        int x = coordinate.X();
        System.out.println("x: " + x);
        int y = coordinate.Y();
        System.out.println("y: " + y);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                System.out.println("check landing spots " + checkLandingSpots(map, new Coordinate(x + i, y + j)));
                if (checkLandingSpots(map, new Coordinate(x + i, y + j))) {
                    System.out.println("Am intrat in if");
                    adjCoordinates.add(new Coordinate(x + i, y + j));
                    System.out.println("Adj coord in if " + adjCoordinates);
                }
            }
        }

        System.out.println("Adj coord before return: " + adjCoordinates);
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
//    @Override
//        public boolean checkLandingSpots(String map, Coordinate coordinate) {
//        int x = coordinate.X();
//        int y = coordinate.Y();
//
////        return Objects.equals(map[x][y], " ");
//        int sqrtOfMapSize = (int) Math.sqrt(map.length());
//
//        String spot = map.split("")[x * sqrtOfMapSize + y]; //(x+1) daca incep de la 1 1
//        return Objects.equals(spot, " ");
//    }

    @Override
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
                System.out.println("map arr i, j " + mapArray[i][j] + " i= " + i + " j= " + j);
                System.out.println("map[" + i + ", " + j + "] = >" + mapArray[i][j]+"<");
            }
        }

        // Check if the spot is valid
        if (x >= 0 && x < sqrtOfMapSize && y >= 0 && y < sqrtOfMapSize) {
            System.out.println("map arr " + mapArray[x][y]);
            return mapArray[x][y] == ' ';
        }

        return false;
    }

}