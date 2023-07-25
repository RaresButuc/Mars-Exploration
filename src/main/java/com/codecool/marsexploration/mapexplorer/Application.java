package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-0.map";
        Coordinate landingSpot = new Coordinate(6, 6);
        MapLoader mapLoader= new MapLoaderImpl();
        ExplorationSimulator explorationSimulator = new ExplorationSimulator();
        InitializeRover initializeRover = new InitializeRover();

        ConfigurationValidatorImpl configurationValidator = new ConfigurationValidatorImpl();
        String mapContent=mapLoader.load(mapFile).toString();

        Configuration mapConfiguration = new Configuration(mapFile, landingSpot, List.of("#", "&", "*", "%"), 2);
        HashMap<String, List<Coordinate>> resources = explorationSimulator.getResources(mapConfiguration);
        MarsRover rover = initializeRover.initializeRover(landingSpot, 2, resources, mapConfiguration);
        SimulationContext simulationContext = new SimulationContext(2, 2, rover, landingSpot, mapFile, List.of("#", "&", "*", "%"));
        explorationSimulator.runSimulation(mapConfiguration);
        System.out.println(explorationSimulator);
    }



}

