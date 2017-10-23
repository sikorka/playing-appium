package com.tigerspike.flickrbrowser.app;

import com.tigerspike.FileHelper;
import com.tigerspike.IOSDriverBuilder;
import com.tigerspike.TagsGenerator;
import com.tigerspike.flickrbrowser.endpoint.FlickrPhotosEndpoint;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Step definitions for features.
 */
public class Steps {

    private App app;
    private IOSDriver driver;
    private FlickrPhotosEndpoint endpoint;

    private List<String> endpointTitles;
    private List<String> appsTitles;
    private String searchTags;



    @Given("^Michelle opens the app on her phone$")
    public void michelle_opens_the_app_on_her_phone() throws Throwable {
        openApp();
    }

    private void openApp() throws Exception {
        driver = createDriver();
        app = createApp(driver);
        endpoint = new FlickrPhotosEndpoint();
    }

    @When("^She submits single tag (.*) to search by$")
    public void she_submits_single_tag_london_to_search_by(String tags) throws Throwable {
        typeAndSubmit(tags);
    }

    @Then("^The photos titles displayed are equal to the ones returned from Photos API$")
    public void the_photos_titles_displayed_are_equal_to_the_ones_returned_from_Photos_API() throws Throwable {

        endpointTitles = endpoint.getTitlesOfPhotosByTags(searchTags);
        appsTitles = app.searchScreen.getTitles(driver);

        assertThat("when search by single tag is performed, " +
                        "then photos displayed are equal photos returned from Photos API",
                appsTitles,
                is(equalTo(endpointTitles)));

        cleanUpDriver();
    }

    @When("^She submits single non-existing tag to search by$")
    public void she_enters_single_tag_non_existing_tag_here_to_search_by() throws Throwable {
        typeAndSubmit(TagsGenerator.getNonExistingTag());
    }

    @Then("^Nothing is displayed in the app$")
    public void nothing_is_displayed_in_the_app() throws Throwable {

        appsTitles = app.searchScreen.getTitles(driver);

        assertThat("when search by non-existing tag is performed, " +
                        "then no photos are displayed",
                appsTitles,
                hasSize(0));

        cleanUpDriver();
    }

    @When("^She enters nothing into search box and submits$")
    public void she_enters_nothing_into_search_box_and_submits() throws Throwable {
        typeAndSubmit("");
    }

    @When("^She enters two tags (.*) to search by$")
    public void sheEntersTwoTagsToSearchBy(String tags) throws Throwable {
        typeAndSubmit(tags);
    }

    //TODO how to reuse SearchTest fields and methods (move somewhere better)
    private void typeAndSubmit(String tags) {
        searchTags = tags;
        app.searchScreen.typeSearchTermAndSubmit(searchTags);
    }

    private App createApp(IOSDriver driver) {
        return new App(driver);
    }

    private IOSDriver createDriver() throws Exception {
        return IOSDriverBuilder.buildDriver(getAppFile());
    }

    private void cleanUpDriver() {
        if (driver == null) return;

        driver.quit();
    }

    private File getAppFile() {
        return FileHelper.fromResources(App.APP_FILE_NAME);
    }

}
