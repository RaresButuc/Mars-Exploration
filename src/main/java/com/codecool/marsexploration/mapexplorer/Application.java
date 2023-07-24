package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-0.map";
        Coordinate landingSpot = new Coordinate(6, 6);
        MapLoader mapLoader= new MapLoaderImpl();
        System.out.println(mapLoader.load(mapFile).toString());
        // Add your code here
    }

}

