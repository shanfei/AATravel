package com.home.aatravel.bdd;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.home.aatravel.bdd.users")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.home.aatravel.bdd.users.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:com.home.aatravel.bdd.users")
public class CucumberIntegrationTest extends CucumberBootstrap {
}
