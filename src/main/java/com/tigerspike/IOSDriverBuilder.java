package com.tigerspike;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static com.tigerspike.PropertiesHelper.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;

/**
 * Builds driver from capabilities in <code>app.properties</code>.
 */
public class IOSDriverBuilder {

    /**
     * Builds driver for <code>appFile</code>.
     *
     * @param appFile the <code>.app</code> file coordinates
     * @return IOSDriver for the app
     * */
    public static IOSDriver buildDriver(File appFile) throws Exception {

        if (appFile == null) return null;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(APPIUM_VERSION, getProperty("APPIUM_VERSION"));
        capabilities.setCapability(PLATFORM_VERSION, getProperty("PLATFORM_NAME"));
        capabilities.setCapability(DEVICE_NAME, getProperty("DEVICE_NAME"));
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, getProperty("NEW_COMMAND_TIMEOUT"));

        capabilities.setCapability(APP, appFile.getAbsolutePath());

        return new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

}
