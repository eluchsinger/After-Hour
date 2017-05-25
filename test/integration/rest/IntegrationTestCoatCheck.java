package integration.rest;

import junit.framework.TestCase;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;
import static play.test.Helpers.*;

public class IntegrationTestCoatCheck extends WithApplication{
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testGetEventById() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/1");
        final Result result = route(request);
        TestCase.assertEquals(OK, result.status());
    }

    @Test
    public void testFetchJacketTwice(){
        final Http.RequestBuilder request1 = new Http.RequestBuilder()
                .method(GET)
                .uri("/fetchJacket/654321");
        final Result result = route(request1);

        TestCase.assertEquals("true", Helpers.contentAsString(result));

        final Http.RequestBuilder request2 = new Http.RequestBuilder()
                .method(GET)
                .uri("/fetchJacket/654321");
        final Result finalResult = route(request2);
        TestCase.assertEquals("false", Helpers.contentAsString(finalResult));
        assertEquals(OK, result.status());
    }
}
