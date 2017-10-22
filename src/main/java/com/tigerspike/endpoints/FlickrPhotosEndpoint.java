package com.tigerspike.endpoints;

import com.google.gson.GsonBuilder;
import com.tigerspike.UrlHelper;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.tigerspike.Log.*;

/**
 * Helps with getting Flickr photos and their titles'.
 */
public class FlickrPhotosEndpoint extends JsonEndpoint {

    public static final String FLICKR_PHOTOS_JSON_URL =
            "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";

    /** Photos returned from encpoint. */
    private Photos photos;

    /**
     * Preps parametrized endpoint URL. Encodes quotes, spaces or other special chars.
     *
     * @param tags tag(s) to query endpoint with
     * @return endpoint URL with tags param
     */
    private URI getEndpointUrlByTags(String tags) {
        URI uri = UrlHelper.createUrl(FLICKR_PHOTOS_JSON_URL, "tags", tags);

        return uri;
    }

    /**
     * Queries Flickr photos endpoint.
     *
     * @param tags tag, or tags separated with commas, to query Flickr photos endpoint with
     * @return a collection of images titles
     */
    public List<String> getTitlesOfPhotosByTag(String tags) {
        photos = getPhotosByTags(tags);

        if (photos == null) return null;

        return photos.getTitles();
    }

    /** Photos class for Gson. */
    class Photos {
        String title;
        List<Photo> items;

        List<String> getTitles() {
            if (items == null || items.size() <= 0)
                return null;

            List<String> list = new ArrayList<String>();
            items.forEach( photo -> list.add(photo.title) );

            return list;
        }

        @Override
        public String toString() {
            //TODO can't use getGson()

            return new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    create().
                    toJson(this);
        }
    }

    /** Photo class for Gson. */
    class Photo {
        String title;
        String tags;
    }

    private Photos getPhotosByTags(String tags) {

        HttpClient client = createHttpClient();

        HttpGet request = requestEndpointByTag(tags);

        HttpResponse response = performRequestAndCheckStatusCode(client, request);

        InputStream content = getResponseContents(response);

        Photos photos = parseResponseContentsToPhotos(content);

        return photos;
    }

    private HttpGet requestEndpointByTag(String tag) {
        URI url = getEndpointUrlByTags(tag);
        info("URL is: " + url);

        HttpGet get = new HttpGet(url);

        return get;
    }

    private InputStream getResponseContents(HttpResponse response) {
        if (response == null) return null;

        try {
            return response.getEntity().getContent();
        } catch (IOException e) {
            info("O oh, something's wrong because: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private Photos parseResponseContentsToPhotos(InputStream content) {
        if (content == null) return null;

        try {
            Reader json = new InputStreamReader(content);

            Photos photos = getGson().fromJson(json, Photos.class);

            content.close();

            return photos;

        } catch (Exception ex) {
            info("Failed to parse JSON because: " + ex.getMessage());
        }

        return null;
    }

    private HttpResponse performRequestAndCheckStatusCode(HttpClient client, HttpGet request) {
        if (client == null || request == null) return null;

        try {
            HttpResponse response = client.execute(request);

            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                return response;
            } else {
                info("Oops, server responded with: " + statusLine.getStatusCode());
            }

            //TODO check JSON content too

        } catch(IOException e) {
            info("Something went wrong because: " + e);
        }

        return null;
    }

    @Override
    public String toString() {
        return photos.toString();
    }

    public static void main(String... args) {
        FlickrPhotosEndpoint e = new FlickrPhotosEndpoint();

        Photos photos = e.getPhotosByTags("u1l2a3l4a5,ulalagdziekurekszesc");
        info(photos);

        info(e.getTitlesOfPhotosByTag("london"));
    }

}
