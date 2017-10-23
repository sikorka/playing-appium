package com.tigerspike.flickrbrowser.app.junit;

import com.tigerspike.FileHelper;
import com.tigerspike.IOSDriverBuilder;
import com.tigerspike.flickrbrowser.app.App;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

import static com.tigerspike.Log.info;

/**
 * Any app's test.
 */
public abstract class AbstractAppTest {

    protected static App createApp(IOSDriver driver) {
        return new App(driver);
    }

    protected static IOSDriver createDriver() throws Exception {
        return IOSDriverBuilder.buildDriver(getAppFile());
    }

    private static File getAppFile() {
        return FileHelper.fromResources(App.APP_FILE_NAME);
    }

    protected static void cleanUpDriver(IOSDriver driver) {
        if (driver == null) return;

        driver.quit();
    }

    /** When a test starts or ends: print test name. */
    @Rule
    public TestWatcher printTestStartAndEnd = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            super.starting(description);
            info("Starting : " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            super.finished(description);
            info("Finished : " + description.getMethodName());
        }

    };

}
