package com.codecool.marsexploration.mapexplorer.rovers.model;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.HashMap;
import java.util.List;

public class MarsRover {
    private final Coordinate currentPosition;
    private final int sight;
    private final HashMap<String, List<Coordinate>> resources;

    private int id;
    private String name;

    private static int counter = 0;

    public MarsRover(Coordinate currentPosition, int sight, HashMap<String, List<Coordinate>> resources) {
        this.id = counter;
        counter++;

        this.name = "rover-" + id;
        this.currentPosition = currentPosition;
        this.sight = sight;
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public String getNamed() {
        return name;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public int getSight() {
        return sight;
    }

    public HashMap<String, List<Coordinate>> getResources() {
        return resources;
    }


}
