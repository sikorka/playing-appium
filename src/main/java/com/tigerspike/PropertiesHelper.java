package com.tigerspike;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Helps with reading properties.
 */
public class PropertiesHelper {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(
                    FileHelper.fromResources("app.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.putAll(System.getProperties());
    }

    /**
     * Gets a property from <code>app.properties</code> merged with system set properties.
     *
     * @param propertyName the name of property
     * @return property value from <code>app.properties</code>
     * */
    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

}
