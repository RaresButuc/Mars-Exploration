package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.*;

public class ExplorationSimulator {
    private SimulationContext simulationContext;
    private ConfigurationValidator configurationValidator;
    private Configuration configuration;

    public ExplorationSimulator(SimulationContext simulationContext, ConfigurationValidator configurationValidator, Configuration configuration) {
        this.simulationContext = simulationContext;
        this.configuration = configuration;
        this.configurationValidator = configurationValidator;
//        this.startExploring();
    }

    public void startExploring() {
        List<Coordinate> visitedCoordonate = new ArrayList<>();
        while (simulationContext.getNumberOfSteps() < simulationContext.getTimeoutSteps()) {
            Random random = new Random();
            List<Coordinate> adjacentCoordinate = configurationValidator.checkAdjacentCoordinate(simulationContext.getRover().getCurrentPosition(), configuration);
            if (adjacentCoordinate.size() > 0) {
                Coordinate roverPosition = simulationContext.getRover().getCurrentPosition();
                Coordinate newRandomRoverPosition = adjacentCoordinate.get(random.nextInt(adjacentCoordinate.size()));
                visitedCoordonate.add(roverPosition);

                if(!new HashSet<>(visitedCoordonate).containsAll(adjacentCoordinate) && !new HashSet<>(adjacentCoordinate).containsAll(visitedCoordonate)){
                    while (visitedCoordonate.contains(newRandomRoverPosition)){
                        newRandomRoverPosition = adjacentCoordinate.get(random.nextInt(adjacentCoordinate.size()));
                    }
                }

                simulationContext.getRover().setCurrentPosition(newRandomRoverPosition);


                //   System.out.println("Rover at : " + roverPosition.X() + ", " + roverPosition.Y() );
                simulationContext.setNumberOfSteps(simulationContext.getNumberOfSteps() + 1);
                FileLogger fileLogger = new FileLogger("src/main/resources/ResultsAfterexploration-0.map");
                fileLogger.logInfo("STEP" + simulationContext.getNumberOfSteps());
                if (configurationValidator.checkAdjacentCoordinate(roverPosition, configuration).size() < 8) {
                    simulationContext.getMonitoredResources().putAll(findResources(configuration, simulationContext.getRover().getCurrentPosition()));
                    for (String symbolKey : simulationContext.getMonitoredResources().keySet()) {
                        List<Coordinate> coordinates = simulationContext.getMonitoredResources().get(symbolKey);
                        System.out.println("Symbol: " + symbolKey);
                        for (Coordinate coordinate : coordinates) {
                            System.out.println("   Coordinate: " + coordinate);
                        }
                    }
                    System.out.println("__________________________________________");
                }
            }
        }
        simulationContext.setExplorationOutcome(ExplorationOutcome.TIMEOUT);
    }

    public HashMap<String, List<Coordinate>> findResources(Configuration configuration, Coordinate currentRoverPosition) {
        List<String> mapLoader = new MapLoaderImpl().readAllLines(configuration.map());
        String map = String.join("", mapLoader);
        // System.out.println("map= "+map);
        HashMap<String, List<Coordinate>> resourcesMap = new HashMap<>();
//        int rows = mapLoader.size();
//        int cols = mapLoader.get(0).length();
        int startX = (currentRoverPosition.X() == 0 ? 0 : currentRoverPosition.X() - 1);
        int startY = (currentRoverPosition.Y() == 0 ? 0 : currentRoverPosition.Y() - 1);
        int stopX = (currentRoverPosition.X() == mapLoader.size() - 1 ? currentRoverPosition.X() : currentRoverPosition.X() + 1);
        int stopY = (currentRoverPosition.Y() == mapLoader.size() - 1 ? currentRoverPosition.Y() : currentRoverPosition.Y() + 1);
        for (int i = startX; i <= stopX; i++) {
            for (int j = startY; j <= stopY; j++) {
                char symbol = map.charAt(i * (mapLoader.size()) + j);
                if (symbol != ' ' && symbol != '\n') {
                    String symbolKey = Character.toString(symbol);
                    Coordinate coordinate = new Coordinate(j, i);
                    resourcesMap.computeIfAbsent(symbolKey, k -> new ArrayList<>()).add(coordinate);
                }
            }
        }


        return resourcesMap;
    }
}
