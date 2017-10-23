package com.tigerspike;

import java.util.Date;

/**
 * Helps with tags.
 */
public class TagsGenerator {

    /**
     * Generated with milliseconds appended to end string.
     * Hardly possible to have such tag already existing.
     * */
    public static String getNonExistingTag() {
        return "nonExistingTag" + new Date().getTime();
    }

}
