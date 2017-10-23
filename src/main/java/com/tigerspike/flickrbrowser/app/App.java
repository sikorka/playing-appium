package com.tigerspike.flickrbrowserapp;

import com.tigerspike.PropertiesHelper;
import com.tigerspike.flickrbrowserapp.screen.SearchScreen;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents <code>FlickrBrowser app</code>.
 */
public class App {
    public static final String APP_FILE_NAME = PropertiesHelper.getProperty("APP_FILE_NAME");

    public SearchScreen searchScreen;
    //PhotoScreen photoScreen;

    public App(WebDriver driver) {
        searchScreen = new SearchScreen();

        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                searchScreen);

        //photoScreen = new PhotoScreen(driver());
    }
}
