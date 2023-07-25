package roversTest;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.InitializeRover;
import com.codecool.marsexploration.mapexplorer.rovers.model.MarsRover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InitializeRoverTest {
    InitializeRover initializeRover = new InitializeRover();

    @ParameterizedTest
    @CsvSource({"7,5","12,1","3,18","18,0","4,2","23,7","30,30"})
    void roverInit(int x,int y) {
        HashMap<String, Coordinate> resources = new HashMap<>();
        resources.put("%", new Coordinate(2, 2));

        MarsRover actual = initializeRover.initializeRover("src/main/resources/exploration-0.map", new Coordinate(x, y), 2, resources);

        assertNotNull(actual);
    }
}