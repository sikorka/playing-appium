package com.tigerspike.flickrbrowser.app.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.CacheLookup;

import java.util.ArrayList;
import java.util.List;

import static com.tigerspike.Log.say;

/**
 * <code>FlickrBrowser app</code>'s main screen.
 */
public class SearchScreen extends AbstractScreen {

    @iOSFindBy(xpath = SEARCH_TEXT_FIELD_XPATH)
    @CacheLookup //the field won't change
    private IOSElement searchTextField;

    @iOSFindBy(xpath = PHOTOS_COLLECTION_XPATH)
    private List<IOSElement> photosList;
    
    
    private static final String SEARCH_TEXT_FIELD_XPATH = WINDOW_XPATH + "/UIASearchBar[1]/UIASearchBar[1]";
    private static final String PHOTOS_COLLECTION_XPATH = WINDOW_XPATH + "/UIACollectionView[1]/UIACollectionCell";

    private static final String TITLE_IN_COLLECTION_ELEMENT_RELATIVE_XPATH = "//UIAStaticText";
    private static final String PUT_INDEX_HERE = "__INDEX_HERE__";
    private static final String PHOTO_BOX_XPATH = PHOTOS_COLLECTION_XPATH + "[" + PUT_INDEX_HERE +"]";
    private static final String TITLE_XPATH_AFTER_CELL = "/UIAStaticText[1]";
    private static final String TITLE_XPATH_TO_ITERATE_OVER = PHOTO_BOX_XPATH + TITLE_XPATH_AFTER_CELL;
    private static final String TITLE_ATTRIBUTE_NAME = "label";

    
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
        if (isShown()) {
            say("Typying in: " + text);

            searchTextField.sendKeys(text);

            //searchTextField.submit(); //'Method is not implemented' exception thrown
            //type 'enter' instead:
            searchTextField.sendKeys("\n");
        }
    }

    /**
     * Collects titles of photos.
     *
     * @return list of photos titles, empty list if none displayed
     * */
    public List<String> getTitles(AppiumDriver driver) {
        List<String> titles = new ArrayList<String>();

        for (int indexInCollection = 1; indexInCollection <= photosList.size(); indexInCollection++) {
            IOSElement titleElement = getTitleElement(indexInCollection, driver);

            String title = titleElement.getAttribute(TITLE_ATTRIBUTE_NAME);

            titles.add(title);
        }

        return titles;
    }

    private IOSElement getTitleElement(int indexInCollection, AppiumDriver driver) {
        if (indexInCollection <= 0 || driver == null) return null;

        return (IOSElement) driver.findElementByXPath(TITLE_XPATH_TO_ITERATE_OVER.
                replace(PUT_INDEX_HERE, String.valueOf(indexInCollection)));
    }

    /**
     * Collects titles of photos. Does not work.
     *
     * @return list of photos titles, empty list if none displayed
     * */
    public List<String> getTitles() {
        List<String> titles = new ArrayList<String>();

        for (MobileElement photo : photosList) {
            IOSElement titleElement = (IOSElement) photo.findElementByXPath(TITLE_IN_COLLECTION_ELEMENT_RELATIVE_XPATH);

            String title = titleElement.getAttribute(TITLE_ATTRIBUTE_NAME);

            titles.add(title);
        }

        // content of title UIAStaticText:
//        name: title text
//        type: UIAStaticText
//        value: title text
//        label: title text
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
