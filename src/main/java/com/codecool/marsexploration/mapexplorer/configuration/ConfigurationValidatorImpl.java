package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationValidatorImpl implements ConfigurationValidator {
    @Override
    public boolean validateConfigurationObject(Configuration configuration) {
        return configuration.steps() > 0
                && configuration.landingSpot().equals(" ")
                && checkAdjacentCoordinate(configuration.landingSpot())
                && !configuration.map().isEmpty()
                && checkSymbols(configuration.symbols());
    }

    private boolean checkAdjacentCoordinate(Coordinate coordinate) {
        int x = coordinate.X();
        int y = coordinate.Y();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0 && new Coordinate(x + i, y + j).equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSymbols(List<String> symbols){
        List<String> allSymbols = List.of("#", "&", "*", "%");
        if(symbols.size() ==0){
            return false;
        }
        for(String symbol: symbols){
            if(!allSymbols.contains(symbol)){
                return false;
            }
        }

        return true;
    }

    private boolean checkLandingSpots(String[][] map, Coordinate coordinate){
        int x=coordinate.X();
        int y= coordinate.Y();
        if(map[x][y])
    }
}
