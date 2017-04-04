import demoData.DemoData;
import org.junit.*;

import play.libs.Json;
import play.mvc.*;
import play.test.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {
    private DemoData serverData;

    @Before
    public void setUp() {
       serverData = DemoData.getInstance();
    }


    @Test
    public void testGetUser() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333/users/1");
            assertTrue(browser.pageSource().contains("Silvio"));
            assertTrue(browser.pageSource().contains("Berlusconi"));
        });
    }

    @Test
    public void testGetAllEvents() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333/events");
            assertEquals(Json.toJson(serverData.getEvents()).toString(), browser.pageSource().toString());
        });
    }

}
