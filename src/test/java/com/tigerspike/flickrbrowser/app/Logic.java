package com.tigerspike.flickrbrowser.app;

import com.tigerspike.FileHelper;
import com.tigerspike.IOSDriverBuilder;
import com.tigerspike.flickrbrowser.app.App;
import com.tigerspike.flickrbrowser.endpoint.FlickrPhotosEndpoint;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.util.List;

import static com.tigerspike.Log.*;

/**
 * Represents above the app (in simulator) logic.
 */
public class Logic {

    protected App app;
    protected IOSDriver mobileSession;
    protected FlickrPhotosEndpoint endpoint;

    protected List<String> endpointTitles;
    protected List<String> appsTitles;
    protected String searchTags;


    protected void openApp() throws Exception {
        try {
            mobileSession = newSession();
        } catch (Exception e) {
            info("Oops. Did the previous session crash? Saying goodbye and opening a new one.");
            byeByeSimulator();
            mobileSession = newSession();
        }

        app = createApp(mobileSession);
        endpoint = new FlickrPhotosEndpoint();
    }

    protected App createApp(IOSDriver driver) {
        return new App(driver);
    }

    protected IOSDriver newSession() throws Exception {
        return IOSDriverBuilder.buildDriver(getAppFile());
    }

    protected File getAppFile() {
        return FileHelper.fromResources(App.APP_FILE_NAME);
    }

    protected void typeAndSubmit(String tags) {
        searchTags = tags;
        app.searchScreen.typeSearchTermAndSubmit(searchTags);
    }

    protected void byeByeSimulator() {
        if (mobileSession == null) return;

        mobileSession.quit();
    }

    protected List<String> getAppsTitles() {
        return app.searchScreen.getTitles(mobileSession);
    }

    protected List<String> getEndpointsTitles() {
        return endpoint.getTitlesOfPhotosByTags(searchTags);
    }

}
