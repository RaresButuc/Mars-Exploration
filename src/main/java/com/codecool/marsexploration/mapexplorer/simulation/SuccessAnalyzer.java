package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;

public class SuccessAnalyzer implements OutcomeAnalyzer {
    @Override
    public boolean hasReachedOutcome(SimulationContext context, Configuration configuration) {
        // Implement your specific success condition for colonization here
        // For example, check if conditions for colonization are met
        // You can use methods from the ConfigurationValidator class or other utilities to check the conditions.
        context.setExplorationOutcome(ExplorationOutcome.COLONIZABLE);
        return context.getMonitoredResources().keySet().contains("&")
                && context.getMonitoredResources().keySet().contains("#")
                && context.getMonitoredResources().keySet().contains("%")
                && context.getMonitoredResources().keySet().contains("*");
    }
}
