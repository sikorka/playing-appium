package com.tigerspike;

import java.net.URI;
import java.net.URISyntaxException;

import static com.tigerspike.Log.info;

/**
 * Helps with managing URLs.
 */
public class UrlHelper {

    /**
     * Joins URL with param. Encodes special chars in paramValue.
     * @param stringUrl full string URL, for example <code>http://google.pl/calendar?abc=def</code>
     * @param paramName single param name, like <code>abc</code> in example above
     * @param paramValue param value - this part will be encoded and can contain any characters
     * @return URI with encoded paramValue
     * */
    public static URI createUrl(String stringUrl, String paramName, String paramValue) {
        URI uri = URI.create(stringUrl);

        try {
            uri = new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    uri.getQuery() + "&" + paramName + "=" + paramValue,
                    null);

            return uri;
        } catch (URISyntaxException e) {
            info("The URL is wrong, surprisingly! Because: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
