package integration.rest;

import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

/**
 * Created by Fabian on 15.05.17.
 */
public class IntegrationTestEventsController extends WithApplication {
    @Test
    public void testGetEvents() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events");
        final Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void getEvent(){
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/1");
        final Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void getEventNotExisting(){
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/100");
        final Result result = route(request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void testTicketCategoriesOnlyAvailable() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/1/ticketCategories?onlyAvailable=true");
        final Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testTicketCategoriesOnlyAvailableWithNonExistingEvent() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/99/ticketCategories?onlyAvailable=true");
        final Result result = route(request);
        assertEquals(BAD_REQUEST, result.status());
    }

    @Test
    public void getEventImage(){
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/events/1/image");
        final Result result = route(request);;
        assertEquals(OK, result.status());
    }
}
