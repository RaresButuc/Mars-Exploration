package roversTest;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitializeRoverTest {
    InitializeRover initializeRover = new InitializeRover();

//    @ParameterizedTest
//    @CsvSource({"7,5","12,1","3,18","18,0","4,2","23,7","30,30", "17,2", "0,2"})
    @Test
    void roverInit() {
        Configuration configurationTrue = new Configuration("src/main/resources/exploration-0.map", new Coordinate(1, 2), List.of("#", "&", "*", "%"), 5);
//MapLoader mapLoader = new MapLoaderImpl();
//mapLoader.load("src/main/resources/exploration-0.map");
//        String map = String.join("", mapLoader);
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl();
        HashMap<String, Coordinate> resources = new HashMap<>();
        resources.put("#", new Coordinate(2, 2));
        if (configurationValidator.validateConfigurationObject(configurationTrue)){

            System.out.println(" config valid "+configurationValidator.checkLandingSpots("src/main/resources/exploration-0.map", new Coordinate(0, 1)));
            MarsRover actual = initializeRover.initializeRover("src/main/resources/exploration-0.map", new Coordinate(2,2), 2, resources);
            System.out.println("Actual "+ actual);
            assertNotNull(actual);
       }


    }
}