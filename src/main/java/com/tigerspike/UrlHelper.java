package com.tigerspike;

import java.net.URI;
import java.net.URISyntaxException;

import static com.tigerspike.Log.info;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Helps with managing URLs.
 */
public class UrlHelper {

    /**
     * Joins URL with another param.
     * Encodes special chars in <code>paramTag</code> and <code>paramValue</code>, see {@link URI}.
     *
     * @param stringUrlWithParam full string URL with param(s), for example <code>http://google.pl/calendar?abc=def</code>
     * @param paramName single param name, like <code>abc</code> in example above
     * @param paramValue param value - this part will be encoded and can contain any characters
     * @return URI with encoded paramValue, <code>null</code> when empty url/paramName or problems with URL creation
     * */
    public static URI addParamToUrl(String stringUrlWithParam, String paramName, String paramValue) {
        URI uri = null;

        if (isBlank(stringUrlWithParam) ||
                isBlank(paramName))
            return null;

        try {
            uri = URI.create(stringUrlWithParam);
        } catch (Exception e) {
                info("Sth's wrong, because: " + e.getMessage());
                e.printStackTrace();
        }

        if (isBlank(uri.getQuery())) {
            info("Url does not contain at least one param: " + stringUrlWithParam);
            return null;
        }

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
