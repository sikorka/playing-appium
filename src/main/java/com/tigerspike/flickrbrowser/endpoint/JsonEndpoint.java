package com.tigerspike.flickrbrowser.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Represents any endpoint returning JSON contents.
 */
public abstract class JsonEndpoint {
    private Gson gson = null;

    protected HttpClient createHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        return builder.build();
    }

    protected Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().
                setPrettyPrinting().
                serializeNulls().
                create();

        return gson;
    }

}
