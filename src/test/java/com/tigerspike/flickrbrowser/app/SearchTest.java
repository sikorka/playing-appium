package com.tigerspike.flickrbrowser.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import static com.tigerspike.Log.*;

/**
 * Flickr Browser app's search test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        format = {"json:target/cucumber.json", "html:target/cucumber-html"}
)
public class SearchTest {

}
