package com.codecool.marsexploration.mapexplorer.database;

import java.util.List;

public interface Resources {
    void add( int steps, int amountOfResources, String outcome);

    void displayAllResourcesFromDatabase();
}
