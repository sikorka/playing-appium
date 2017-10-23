package com.tigerspike.flickrbrowserapp;

import io.appium.java_client.ios.IOSDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Checks whether app loads.
 */
public class FlickrBrowserAppLoadTest extends FlickrBrowserAppTest {

    private static App app;
    private static IOSDriver driver;

    @BeforeClass
    public static void setup() throws Exception {
        driver = createDriver();
        app = createApp(driver);
    }

    @Test
    public void whenAppRun_thenSearchScreenShown() {
        assertThat("when app run, then photos screen opens",
                app.searchScreen.isShown(),
                is(true));
    }

    @AfterClass
    public static void tearDown() {
        cleanUpDriver(driver);
    }

}
