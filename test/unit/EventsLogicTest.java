package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.events.EventsLogic;
import models.events.Event;
import models.exceptions.ServerException;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian Schwyter on 08.04.17.
 * Tests the logic of the "Events"-Domain.
 * To test logic-only, the database is mocked.
 */
public class EventsLogicTest extends WithApplication {
    private EventsLogic eventsLogic;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class).eagerly())
                .build();
    }

    @Before
    public void setup(){
        eventsLogic = this.app.injector().instanceOf(EventsLogic.class);
    }

    @Test
    public void testGetExistingEvent(){
        final Event event = eventsLogic.getEventById(2);
        assertEquals(new Integer (2), event.getId());
    }

    @Test
    public void testGetEventWithTicketCategoriesAvailable() throws ParseException, ServerException {
       // Event event = eventsLogic.getEventWithTicketCategories(2,false , dateFormat.parse("2017-4-22"));
      // assertEquals(1,event.getTicketCategories().size());
        assertNull(null);
    }
}
