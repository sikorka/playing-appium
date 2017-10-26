package com.tigerspike;

import java.util.Date;

/**
 * Helps with generating Flickr tags.
 */
public class TagsGenerator {

    /**
     * Generated with milliseconds appended to end string.
     * Hardly possible to have such tag already existing, hence considered
     * not existing in Flickr yet.
     * @return a non-existing Flickr photo tag
     *
     * */
    public static String getNonExistingTag() {
        return "nonExistingTag" + new Date().getTime();
    }

}
