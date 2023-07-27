package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;

public class LackOfResourcesAnalyzer implements OutcomeAnalyzer {
    @Override
    public boolean hasReachedOutcome(SimulationContext context, Configuration configuration) {
        // Implement your specific condition for lack of resources here
        // For example, check if the rover has almost explored the whole map and no right condition is found.
        return false; // Replace this with the actual condition check
    }
}
