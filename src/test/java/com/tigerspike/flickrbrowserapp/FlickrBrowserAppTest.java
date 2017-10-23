package com.tigerspike.flickrbrowserapp;

import com.tigerspike.FileHelper;
import com.tigerspike.endpoint.FlickrPhotosEndpoint;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


/**
 * Compares endpoint's photos with app's photos.
 */
public class FlickrBrowserAppTest {
    private static App app;
    private static IOSDriver driver;
    private static FlickrPhotosEndpoint endpoint;

    @BeforeClass
    public static void setup() throws Exception {
        driver = buildDriver();

        app = new App(driver);

        endpoint = new FlickrPhotosEndpoint();
    }

    private static IOSDriver buildDriver() throws Exception {
        File appFile = FileHelper.fromResources(App.APP_FILE_NAME);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.5.3");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
        capabilities.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1200);

        return new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    @Ignore
    public void whenAppRun_thenPhotosGridShown() {
        assertThat("when app run, then photos grid screen opens",
                app.searchScreen.isShown(),
                is(true));
    }

    @Test
    //@Ignore("cause: app.searchScreen.getTitles() not implemented")
    public void whenSearchByTagSubmitted_thenTitlesDisplayedEqualPhotosEndpointTitles() {
        //given
        String tags = "u1l2a3l4a5";

        List<String> titles = endpoint.getTitlesOfPhotosByTags(tags);

        //when
        app.searchScreen.typeSearchTermAndSubmit(tags);

        //then
        assertThat("when search by tag(s) performed, " +
                        "then photos displayed are equal photos returned from Flickr photos endpoint",
                app.searchScreen.getTitles(),
                is(equalTo(titles)));
    }

    @AfterClass
    public static void tearDown() {
        if (driver == null) return;

        driver.quit();
    }
}
