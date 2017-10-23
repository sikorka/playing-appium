package com.tigerspike.flickrbrowser.app.junit;

import com.tigerspike.flickrbrowser.app.App;
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
@Ignore("cause: slows cucumber tests down")
public class LoadAppTest extends AbstractAppTest {

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
