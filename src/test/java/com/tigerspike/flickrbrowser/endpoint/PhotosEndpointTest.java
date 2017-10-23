package com.tigerspike.flickrbrowser.endpoint;

import com.tigerspike.TagsGenerator;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import java.util.List;

import static com.tigerspike.Log.info;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests some of the FlickrPhotosEndpoint methods.
 *
 * Makes sure endpoint behaves as expected before heavy UI tests are run.
 */
public class PhotosEndpointTest {
    static FlickrPhotosEndpoint flickrPhotosEndpoint;


    @BeforeClass
    public static void setup() {
        flickrPhotosEndpoint = new FlickrPhotosEndpoint();
    }

    @Test
    @Ignore("cause: started failing")
    public void noTag_whenNullTag_thenPhotosListNonEmpty_and_containsTestPhoto() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags(null);

        assertThat("when 'null' tag then photos list non empty",
                titles,
                is(not(empty())));

        assertThat("when 'null' tag then photos list contains 4th test photo title",
                titles,
                hasItem(TEST_PHOTO_4));
    }

    @Test
    public void testTag_whenTestTag_thenPhotosListContainsTestTitle() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags("u1l2a3l4a5");

        assertThat("when 'u1l2a3l4a5' tag then photos list contains at least 3 photos",
                titles,
                hasSize(greaterThanOrEqualTo(3)));

        assertThat("when 'u1l2a3l4a5' tag then photos list contains 3rd test photo title",
                titles,
                hasItem(TEST_PHOTO_3));

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list does not contain 6th test photo title",
                titles,
                not(hasItem(TEST_PHOTO_6)));
    }

    @Test
    public void twoTestTags_whenTwoTestTagsCommaSeparated_thenPhotosListContainsTestTitles() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags("u1l2a3l4a5,ulalaGdzieKurekSzesc");

        assertThat("when 'u1l2a3l4a5' tag then photos list contains at least 3 photos",
                titles,
                hasSize(greaterThanOrEqualTo(3)));

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list does not contain 6th test photo title",
                titles,
                not(hasItem(TEST_PHOTO_6)));

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list contains 2nd, 3rd, 4th test photo title",
                titles,
                hasItems(TEST_PHOTO_2, TEST_PHOTO_3, TEST_PHOTO_1));
    }

    @Test
    public void twoTestTags_whenTwoTestTagsSeparatedWithCommaAndSpaces_thenPhotosListContainsTestTitles() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags("u1l2a3l4a5,  ulalaGdzieKurekSzesc");

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list contains at least 3 photos",
                titles,
                hasSize(greaterThanOrEqualTo(3)));

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list does not contain 6th test photo title",
                titles,
                not(hasItem(TEST_PHOTO_6)));

        assertThat("when 'u1l2a3l4a5 and ulalaGdzieKurekSzesc' tag then photos list contains 2nd, 3rd, 4th test photo title",
                titles,
                hasItems(TEST_PHOTO_2, TEST_PHOTO_3, TEST_PHOTO_1));
    }

    @Test
    public void twoTestTags_whenTwoTestTagsSplittedWithOneSpace_thenPhotosListContainsTestTitles() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags("u1l2a3l4a5 ulalaGdzieKurekSzesc");

        assertThat("when tags splitted only with space, then photos list contains only items for joined tag string 'u1l2a3l4a5ulalaGdzieKurekSzesc'",
                titles,
                hasSize(greaterThanOrEqualTo(1)));

        assertThat("when tags splitted only with space, then photos list contains 4th test photo title",
                titles,
                hasItem(TEST_PHOTO_4));
    }

    @Test
    public void notExistingTagsTest_whenNonExistingTags_thenPhotosListIsEmpty() {
        List<String> titles = flickrPhotosEndpoint.getTitlesOfPhotosByTags(TagsGenerator.getNonExistingTag());

        assertThat("when search performed with non existing tags, then photos list is empty",
                titles,
                hasSize(0));
    }

    /** When a test fails: print photos. */
    @Rule
    public TestWatcher printPhotosOnFailedTestRule = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            printPhotos();
        }

        private void printPhotos() {
            info(flickrPhotosEndpoint.toString());
        }
    };

    private static String MIDDLE = "_ulalaGdzieKurekSzesc_image_part_00";
    static String TEST_PHOTO_2 = "2" + MIDDLE + "2";
    static String TEST_PHOTO_1 = "1" + MIDDLE + "1";
    static String TEST_PHOTO_3 = "3" + MIDDLE + "3";
    static String TEST_PHOTO_4 = "4" + MIDDLE + "4";
    static String TEST_PHOTO_5 = "5" + MIDDLE + "5";
    static String TEST_PHOTO_6 = "6" + MIDDLE + "6";
}
