package com.cucumber.framework.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author manoj.jena
 *
 *         11-Nov-2023
 *
 */
@CucumberOptions(features = { "classpath:featurefile/CheckersGame.feature" }, glue = {
		"classpath:com.cucumber.framework.stepdefinition",
		"classpath:com.cucumber.framework.helper" }, plugin = { "pretty",
		"json:target/LaptopFeatureRunner.json" })
public class CheckersGameRunner extends AbstractTestNGCucumberTests {
}

