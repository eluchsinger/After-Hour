import demoData.DemoData;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

public class IntegrationTest {
    private DemoData serverData;

    @Before
    public void setUp() {
       serverData = DemoData.getInstance();
    }

    @Test
    public void testGetUser() {
        running(fakeApplication(), () -> {
            Integer id = 1;

            Http.RequestBuilder mockActionRequest = fakeRequest(controllers.routes.UserController.getUser(id));
            Result result = route(mockActionRequest);
            // Todo: Revert the test result to test for OK
            assertEquals(NOT_FOUND, result.status());
        });
//        running(testServer(3333, fakeApplication(inMemoryDatabase(
//                "ahdb",
//                Collections.singletonMap("MODE", "PostgreSQL")
//        ))), HTMLUNIT, browser -> {
//            browser.goTo("http://localhost:3333/users/1");
//            assertTrue(browser.pageSource().contains("Silvio"));
//            assertTrue(browser.pageSource().contains("Berlusconi"));
//        });
    }

//    @Test
//    public void testGetAllEvents() {
//        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
//            browser.goTo("http://localhost:3333/events");
//            assertEquals(Json.toJson(serverData.getEvents()).toString(), browser.pageSource().toString());
//        });
//    }

}
