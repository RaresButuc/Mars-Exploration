package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.HashMap;
import java.util.List;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-0.map";
        HashMap<String, List<Coordinate>> resources = new HashMap<>();
        Coordinate landingSpot = new Coordinate(6, 6);
        MapLoader mapLoader= new MapLoaderImpl();
        ConfigurationValidatorImpl configurationValidator = new ConfigurationValidatorImpl();
        Configuration mapConfiguration = new Configuration(mapFile, landingSpot, List.of("#", "&", "*", "%"), 30);


        if(configurationValidator.checkLandingSpots(landingSpot,mapConfiguration)) {
            InitializeRover initializeRover = new InitializeRover();
            String mapContent=mapLoader.load(mapFile).toString();

//            HashMap<String, List<Coordinate>> resources = explorationSimulatorNotUsed.getResources(mapConfiguration);
            MarsRover rover = initializeRover.initializeRover(landingSpot, 2, resources, mapConfiguration);
            SimulationContext simulationContext = new SimulationContext(0, 100, rover, landingSpot, mapFile, resources);
//        explorationSimulatorNotUsed.runSimulation(mapConfiguration, 2);
            ExplorationSimulator explorationSimulator = new ExplorationSimulator(simulationContext,configurationValidator,mapConfiguration);
            explorationSimulator.startExploring();
//        explorationSimulator.getResources(mapConfiguration);
            System.out.println(rover.getCurrentPosition());
        }else {
            System.out.println("Invalid landing Spot");}

    }



}

