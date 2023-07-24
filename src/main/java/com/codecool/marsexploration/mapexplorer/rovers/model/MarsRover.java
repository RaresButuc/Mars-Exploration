package com.codecool.marsexploration.mapexplorer.rovers.model;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.HashMap;

public class MarsRover {
    private final int id;
    private final Coordinate currentPosition;
    private final int sight;
    private final HashMap<String, Coordinate> resources;

    public MarsRover(int id, Coordinate currentPosition, int sight, HashMap<String, Coordinate> resources) {
        this.id = id;
        this.currentPosition = currentPosition;
        this.sight = sight;
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public int getSight() {
        return sight;
    }

    public HashMap<String, Coordinate> getResources() {
        return resources;
    }

}
