package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;

public class TimeoutAnalyzer implements OutcomeAnalyzer {
    @Override
    public boolean hasReachedOutcome(SimulationContext context, Configuration configuration) {
        return context.getNumberOfSteps() >= context.getTimeoutSteps();
    }
}
