package com.tigerspike.flickrbrowserapp.junit;

import com.tigerspike.endpoint.FlickrPhotosEndpoint;
import com.tigerspike.flickrbrowserapp.App;
import io.appium.java_client.ios.IOSDriver;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.Arrays;
import java.util.List;

import static com.tigerspike.Log.info;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


/**
 * Compares endpoint's photos with app's photos.
 */
@Ignore("cause: slows cucumber tests down")
public class AppSearchTest extends AppTest {

    protected static App app;
    protected static IOSDriver driver;

    protected static FlickrPhotosEndpoint endpoint;

    protected List<String> endpointTitles;
    protected List<String> appsTitles;
    protected String searchTags;


    @BeforeClass
    public static void setupClass() throws Exception {
        driver = createDriver();
        app = createApp(driver);
        endpoint = new FlickrPhotosEndpoint();
    }


    @Test
    public void whenSearchByTagSubmitted_thenTitlesDisplayedEqualPhotosEndpointTitles() {
        //given
        searchTags = "u1l2a3l4a5";

        endpointTitles = endpoint.getTitlesOfPhotosByTags(searchTags);

        //when
        app.searchScreen.typeSearchTermAndSubmit(searchTags);

        //then
        appsTitles = app.searchScreen.getTitles(driver);

        assertThat("when search by single tag is performed, " +
                        "then photos displayed are equal photos returned from Flickr photos endpoint",
                appsTitles,
                is(equalTo(endpointTitles)));

    }

    /** When a test fails: print endpoint's photos titles returned by endpoint. */
    @Rule
    public TestWatcher printPhotosTitlesOnFailedTestRule = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            printPhotos();
        }

        private void printPhotos() {
            info("Search by: " + searchTags);

            info("Endpoint's titles: " +
                    ((endpointTitles == null) ? null :
                    Arrays.toString(endpointTitles.toArray())));

            info("App's titles: " +
                    ((appsTitles == null) ? null :
                    Arrays.toString(appsTitles.toArray())));
        }
    };

    @AfterClass
    public static void tearDown() {
        cleanUpDriver(driver);
    }

    protected void typeAndSubmit(String tags) {
        searchTags = tags;
        app.searchScreen.typeSearchTermAndSubmit(searchTags);
    }

}
