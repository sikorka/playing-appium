package com.tigerspike.flickrbrowser.app;

import com.tigerspike.flickrbrowser.app.screen.SearchScreen;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents <code>FlickrBrowser app</code>.
 */
public class App {
    public static final String APP_FILE_NAME = "FlickrBrowser-cal.app";

    public SearchScreen searchScreen;
    //PhotoScreen photoScreen;

    public App(WebDriver driver) {
        if (driver == null) return;

        searchScreen = new SearchScreen();

        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                searchScreen);

        //photoScreen = new PhotoScreen(driver());
    }
}
