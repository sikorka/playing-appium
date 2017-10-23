package com.tigerspike.flickrbrowserapp.steps;

import com.tigerspike.TagsGenerator;
import com.tigerspike.endpoint.FlickrPhotosEndpoint;
import com.tigerspike.flickrbrowserapp.App;
import com.tigerspike.flickrbrowserapp.junit.AppTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Step definitions for features.
 */
public abstract class StepDefinitions extends AppTest {

    protected static App app;
    protected static IOSDriver driver;

    protected static FlickrPhotosEndpoint endpoint;

    protected List<String> endpointTitles;
    protected List<String> appsTitles;
    protected String searchTags;



    @Given("^Kasia opens the app on her phone$")
    public void michelle_opens_the_app_on_her_phone() throws Throwable {
        openApp();
    }

    private static void openApp() throws Exception {
        driver = createDriver();
        app = createApp(driver);
    }

    @When("^She submits single tag '(.*)' to search by$")
    public void she_submits_single_tag_london_to_search_by(String tags) throws Throwable {
        typeAndSubmit(tags);
    }

    @Then("^The photos titles displayed are equal to the ones returned from Photos API$")
    public void the_photos_titles_displayed_are_equal_to_the_ones_returned_from_Photos_API() throws Throwable {
        endpoint = new FlickrPhotosEndpoint();
        endpointTitles = endpoint.getTitlesOfPhotosByTags(searchTags);

        appsTitles = app.searchScreen.getTitles(driver);

        assertThat("when search by single tag is performed, " +
                        "then photos displayed are equal photos returned from Photos API",
                appsTitles,
                is(equalTo(endpointTitles)));
    }

    @When("^She enters single non-existing tag to search by$")
    public void she_enters_single_tag_non_existing_tag_here_to_search_by() throws Throwable {
        typeAndSubmit(TagsGenerator.getNonExistingTag());
    }

    @Then("^Nothing is displayed in the app$")
    public void nothing_is_displayed_in_the_app() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^She enters nothing into search box and submits$")
    public void she_enters_nothing_into_search_box_and_submits() throws Throwable {
        typeAndSubmit("");
    }

    @When("^She enters two <tags> to search by$")
    public void sheEntersTwoTagsToSearchBy(String tags) throws Throwable {
        typeAndSubmit(tags);
    }

    //TODO reuse AppSearchTest fields and methods, move somewhere better
    protected void typeAndSubmit(String tags) {
        searchTags = tags;
        app.searchScreen.typeSearchTermAndSubmit(searchTags);
    }

}
