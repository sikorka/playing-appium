package com.tigerspike;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static com.tigerspike.Log.*;

/**
 * Helps with file related operations.
 */
public class FileHelper {

    /**
     * Returns a file under <code>resources/</code> directory, given file name.
     * Through URI it makes sure to encode spaces and other special chars in file name.
     *
     * @param fileNameUnderResources file name, for example 'app.zip'
     * @return file instance or <code>null</code> on error
     * */
    public static File fromResources(String fileNameUnderResources) {
        try {
            return new File(
                    FileHelper.class.getClassLoader().
                            getResource(fileNameUnderResources).
                            toURI().
                            toURL().
                            getFile());

        } catch (MalformedURLException | URISyntaxException | NullPointerException e) {
            info("Real weird, there's sth wrong with the file (" + fileNameUnderResources + ") path: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
