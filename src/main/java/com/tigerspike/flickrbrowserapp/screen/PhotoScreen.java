package com.tigerspike.flickrbrowserapp.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * <code>FlickrBrowser app</code>'s photo screen.
 */
public class PhotoScreen extends AbstractScreen {

    MobileElement doneButton;

    public void back() {
        //TODO

    }

    public void close() {
        back();
    }
}
