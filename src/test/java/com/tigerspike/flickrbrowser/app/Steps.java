package com.tigerspike.flickrbrowser.app;

import com.tigerspike.TagsGenerator;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static com.tigerspike.Log.*;

/**
 * Step definitions for features.
 */
public class Steps extends Logic {


    @Given("^Michelle opens the app on her phone$")
    public void michelle_opens_the_app_on_her_phone() throws Throwable {

        openApp();
    }

    @Then("^The photos titles displayed are equal to the ones returned from Photos API$")
    public void the_photos_titles_displayed_are_equal_to_the_ones_returned_from_Photos_API() throws Throwable {

        assertThat("when search by " + description + " is performed, " +
                        "then photos displayed are equal photos returned from Photos API",
                getAppsTitles(),
                is(equalTo(getEndpointsTitles())));
    }

    @When("^She submits single non-existing tag to search by$")
    public void she_enters_non_existing_tag_to_search_by() throws Throwable {

        typeAndSubmit(TagsGenerator.getNonExistingTag());
    }

    @Then("^Nothing is displayed in the app$")
    public void nothing_is_displayed_in_the_app() throws Throwable {

        assertThat("when search by non-existing tag is performed, " +
                        "then no photos are displayed",
                getAppsTitles(),
                hasSize(0));
    }

    @When("^She enters nothing into search box and submits$")
    public void she_enters_nothing_into_search_box_and_submits() throws Throwable {

        typeAndSubmit("");
    }

    @When("^She submits <(.*)> '(.*)' to search by$")
    public void she_submits_tags_to_search_by(String description, String tags) throws Throwable {
        this.description = description;

        typeAndSubmit(tags);
    }

    @Then("^The photos titles displayed are NOT equal to the ones returned from Photos API$")
    public void thePhotosTitlesDisplayedAreNOTEqualToTheOnesReturnedFromPhotosAPI() throws Throwable {

        assertThat("when search by " + description + " is performed, " +
                        "then photos displayed are equal photos returned from Photos API",
                getAppsTitles(),
                is(not(equalTo(getEndpointsTitles()))));
    }

    @When("^She enters tags separated with spaces but no comma '(.*)' to search by$")
    public void sheEntersTagsSeparatedWithSpacesButNoCommaToSearchBy(String tags) throws Throwable {

        typeAndSubmit(tags);
    }


    @Before
    public void setup() {
        say("Running scenario ...");
    }

    @After
    public void teardown() {
        byeByeSession();
        say("Scenario ran.");
    }

}
