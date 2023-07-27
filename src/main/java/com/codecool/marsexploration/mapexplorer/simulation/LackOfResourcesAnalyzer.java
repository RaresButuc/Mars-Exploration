package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;

public class LackOfResourcesAnalyzer implements OutcomeAnalyzer {
    @Override
    public boolean hasReachedOutcome(SimulationContext context, Configuration configuration) {
        context.setExplorationOutcome(ExplorationOutcome.COLONIZABLE);
        return context.getMonitoredResources().keySet().contains("&")
                && context.getMonitoredResources().keySet().contains("#")
                && context.getMonitoredResources().keySet().contains("%")
                && context.getMonitoredResources().keySet().contains("*");
    }
}
