package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        for(int i=0; i<3; i++){
            String mapFile = workDir + "/resources/exploration-"+i+".map";
            HashMap<String, List<Coordinate>> resources = new HashMap<>();
            Random random = new Random();
            int x = random.nextInt(32);
            int y = random.nextInt(32);
            Coordinate landingSpot = new Coordinate(x, y);
            MapLoader mapLoader= new MapLoaderImpl();
            ConfigurationValidatorImpl configurationValidator = new ConfigurationValidatorImpl();
            Configuration mapConfiguration = new Configuration(mapFile, landingSpot, List.of("#", "&", "*", "%"), 30);

            if(configurationValidator.checkLandingSpots(landingSpot,mapConfiguration)) {
                InitializeRover initializeRover = new InitializeRover();
                String mapContent=mapLoader.load(mapFile).toString();

//            HashMap<String, List<Coordinate>> resources = explorationSimulatorNotUsed.getResources(mapConfiguration);
                MarsRover rover = initializeRover.initializeRover(landingSpot, 2, resources, mapConfiguration);
                SimulationContext simulationContext = new SimulationContext(0, 60, rover, landingSpot, mapFile, resources);
//        explorationSimulatorNotUsed.runSimulation(mapConfiguration, 2);
                FileLogger fileLogger = new FileLogger("src/main/resources/ResultsAfterExploration-"+i+".map");
                ExplorationSimulator explorationSimulator = new ExplorationSimulator(fileLogger, simulationContext,configurationValidator,mapConfiguration);
                explorationSimulator.startExploring();
                System.out.println("File ResultsAfterExploration-"+i+".map successful created.");
            }else {
                System.out.println("Invalid landing Spot");
            }
        }

    }



}

