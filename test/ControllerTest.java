import dal.UsersRepository;
import dal.mocks.UsersRepositoryMock;
import demoData.DemoData;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian on 03.04.17.
 */
public class ControllerTest extends WithApplication {
    private DemoData demoData;
    private Application application;

    @Before
    public void initalize(){
        demoData = DemoData.getInstance();
        this.application = new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .build();
    }

    @Test
    public void testGetExistingUser() {
        Helpers.running(application, () -> {
            RequestBuilder request = new RequestBuilder()
                    .method(GET)
                    .uri("/users/1");
            Result result = route(request);
            assertEquals(OK, result.status());
        });
    }

    @Test
    public void testGetNonExistingUser() {
        Helpers.running(application, () -> {
            RequestBuilder request = new RequestBuilder()
                    .method(GET)
                    .uri("/users/123123123");
            Result result = route(request);
            assertEquals(NOT_FOUND, result.status());
        });
    }

    @Test
    public void testGetEvents() {
        Helpers.running(application, () -> {
           RequestBuilder request = new RequestBuilder()
                   .method(GET)
                   .uri("/events");
           Result result = route(request);
           assertEquals(OK, result.status());
        });
//        Helpers.running(Helpers.fakeApplication(), () -> {
//            RequestBuilder request = new RequestBuilder()
//                    .method(GET)
//                    .uri("/events");
//
//            Result result = route(request);
//            assertEquals(200, result.status());
//            assertEquals("application/json" ,result.contentType().get());
//        });
    }
}
