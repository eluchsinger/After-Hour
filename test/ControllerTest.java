import akka.stream.Materializer;
import demoData.DemoData;
import org.junit.Before;
import org.junit.Test;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Http.RequestBuilder;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.*;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

/**
 * Created by Fabian on 03.04.17.
 */
public class ControllerTest extends WithApplication {
    private DemoData demoData;

    @Before
    public void initalize(){
        demoData = DemoData.getInstance();
    }

    @Test
    public void testGetEvents() {
        Helpers.running(Helpers.fakeApplication(), () -> {
            RequestBuilder request = new RequestBuilder()
                    .method(GET)
                    .uri("/events");

            Result result = route(request);
            assertEquals(200, result.status());
            assertEquals("application/json" ,result.contentType().get());
        });
    }
}
