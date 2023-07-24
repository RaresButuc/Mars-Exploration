package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface MapLoader {
    Map load(String mapFile);

}
