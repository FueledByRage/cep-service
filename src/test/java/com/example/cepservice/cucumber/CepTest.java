package com.example.cepservice.cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/integration.feature", glue = "com.example.cepservice.cucumber")
public class CepTest {
}
