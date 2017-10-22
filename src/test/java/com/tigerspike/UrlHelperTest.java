package com.tigerspike;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Checks URL creation.
 */
public class UrlHelperTest {

    @Test
    public void whenUrlAndParamNameNull_thenNullUrl() {
        URI createdUri = UrlHelper.addParamToUrl(null, null, null);

        assertThat("when url and param name null, then URL null",
                createdUri,
                is(equalTo(null)));
    }

    @Test
    public void whenUrlAndParamNameEmpty_thenNullUrl() {
        URI createdUri = UrlHelper.addParamToUrl("", "   ", " ");

        assertThat("when url or param name empty, then URL null",
                createdUri,
                is(equalTo(null)));
    }

    @Test
    public void whenTagValueNull_thenUrlCreated() {
        URI createdUri = UrlHelper.addParamToUrl("http://a.bc?aa=bb", "xyz", null);

        assertThat("when tag value null, then URL created",
                createdUri.toString(),
                is(equalTo("http://a.bc?aa=bb&xyz=null")));
    }

    @Test
    public void whenUrlHasNoParams_thenUrlNull() {
        URI createdUri = UrlHelper.addParamToUrl("http://a.bc", "xyz", null);

        assertThat("when url has no params, then URL null",
                createdUri,
                is(equalTo(null)));
    }

    @Test
    public void whenTagValueNotEmptyAndWithSpecialChars_thenUrlCreatedWithProperEncoding() {
        URI createdUri = UrlHelper.addParamToUrl("http://a.bc?aa=bb", "xy z", "a/ \"");

        assertThat("when tag value null, then URL created",
                createdUri.toString(),
                is(equalTo("http://a.bc?aa=bb&xy%20z=a/%20%22")));
    }

}
