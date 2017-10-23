package com.tigerspike.flickrbrowserapp.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>FlickrBrowser app</code>'s main screen.
 */
public class SearchScreen extends AbstractScreen {

    private static final String SEARCH_TEXT_FIELD_XPATH =   WINDOW_XPATH + "/UIASearchBar[1]/UIASearchBar[1]";
    private static final String PHOTOS_XPATH =         WINDOW_XPATH + "/UIACollectionView[1]/UIACollectionCell";
    private static final String PHOTO_BOX_XPATH =           PHOTOS_XPATH + "[__INDEX_HERE__]";
    private static final String PHOTO_TITLE_XPATH_RELATIVE =     "//UIAStaticText[1]";

    @iOSFindBy(xpath = SEARCH_TEXT_FIELD_XPATH)
    @CacheLookup //the field won't change
    private IOSElement searchTextField;

    @iOSFindBy(xpath = PHOTOS_XPATH)
    private List<IOSElement> photosGrid;


    /**
     * Tells whether the screen is displayed.
     *
     * @return <code>true</code> if search text field is displayed, <code>false</code> otherwise
     * */
    public boolean isShown() {
        return searchTextField.isDisplayed();
    }

    /**
     * Types <code>text</code> in search and submits.
     *
     * @param text to search by
     * */
    public void typeSearchTermAndSubmit(String text) {
        searchTextField.sendKeys(text);

        //searchTextField.submit(); //'Method is not implemented' exception thrown
        //type 'enter' instead:
        searchTextField.sendKeys("\n");
    }

    /**
     * Collects titles of photos.
     *
     * @return list of photos titles, empty list if none displayed
     * */
    public List<String> getTitles() {
        List<String> titles = new ArrayList<String>();

        for (MobileElement photo : photosGrid) {
            MobileElement titleElement = photo.findElementByXPath(PHOTO_TITLE_XPATH_RELATIVE + "/@label");
            titles.add(titleElement.getText());
        }

//        name: 2_ulalaGdzieKurekSzesc_image_part_002
//        type: UIAStaticText
//        value: 2_ulalaGdzieKurekSzesc_image_part_002
//        label: 2_ulalaGdzieKurekSzesc_image_part_002
//        hint:
//        enabled: true
//        visible: true
//        valid: true
//        location: {0, 127}
//        size: {100, 37}
//        xpath: //UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAStaticText[1]


        return titles;
    }

}
