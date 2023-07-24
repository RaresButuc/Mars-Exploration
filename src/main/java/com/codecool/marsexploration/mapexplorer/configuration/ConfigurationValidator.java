package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;

import java.time.Duration;

public interface ConfigurationValidator {
    boolean validateConfigurationObject(Configuration configuration);
}
