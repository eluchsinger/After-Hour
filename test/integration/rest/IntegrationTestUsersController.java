package integration.rest;

import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

/**
 * Created by Fabian on 18.05.17.
 */
public class IntegrationTestUsersController extends WithApplication {
    @Test
    public void testGetProfileImage() {
        final Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/users/1/image");
        final Result result = route(request);
        assertEquals("image/jpeg", result.contentType().get());
        assertEquals(OK, result.status());
    }
}
