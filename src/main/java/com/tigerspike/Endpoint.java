package com.tigerspike;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Represents any endpoint returning JSON contents.
 */
public abstract class Endpoint {
    private Gson gson = null;

    protected HttpClient createHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        return builder.build();
    }

    protected Gson getGson() {
        //if (gson == null)
            gson = new GsonBuilder().
                setPrettyPrinting().
                serializeNulls().
                create();

        return gson;
    }

}
