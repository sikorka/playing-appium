package com.tigerspike.flickrbrowserapp.screen;

import java.util.List;

/**
 * <code>FlickrBrowser app</code>'s main screen.
 */
public class MainScreen extends Screen {

    private static final String SEARCH_TEXT_FIELD_XPATH =   WINDOW_XPATH + "/UIASearchBar[1]/UIASearchBar[1]";
    private static final String PHOTO_BOX_XPATH =           WINDOW_XPATH + "/UIACollectionView[1]/UIACollectionCell[__INDEX_HERE__]";
    private static final String PHOTO_BOX_TITLE_XPATH =     PHOTO_BOX_XPATH + "/UIAStaticText[1]";

    Object searchTextField;
    Object photoX;
    Object photoXtitle;

    public List<String> getTitles() {
        //TODO

        return null;
    }

    public void typeSearchTermAndSubmit() {
        //TODO

    }
}
