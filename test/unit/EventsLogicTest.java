package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.coatchecks.CoatChecksRepository;
import dal.coatchecks.CoatChecksRepositoryMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.events.EventsLogic;
import models.events.Event;
import models.exceptions.EventDoesNotExistServerException;
import models.exceptions.ServerException;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.ParseException;

import static junit.framework.TestCase.assertEquals;
import static play.inject.Bindings.bind;
import static utils.DateGenerator.generateDate;

/**
 * Created by Fabian Schwyter on 08.04.17.
 * Tests the logic of the "Events"-Domain.
 * To test logic-only, the database is mocked.
 */
public class EventsLogicTest extends WithApplication {
    private EventsLogic eventsLogic;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(CoatChecksRepository.class).to(CoatChecksRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class).eagerly())
                .build();
    }

    @Before
    public void setup(){
        eventsLogic = this.app.injector().instanceOf(EventsLogic.class);
    }

    @Test
    public void testGetExistingEvent() throws EventDoesNotExistServerException {
        final Event event = eventsLogic.getEventById(2);
        assertEquals(new Integer (2), event.getId());
    }

    @Test (expected = EventDoesNotExistServerException.class)
    public void testGetNonExistingEvent() throws EventDoesNotExistServerException {
        eventsLogic.getEventById(323132);
    }

    @Test
    public void testGetEventWithTicketCategoriesOnlyAvailable() throws ParseException, ServerException {
       Event event = eventsLogic.getEventWithTicketCategories(1,true , generateDate(2));
       assertEquals(1,event.getTicketCategories().size());
    }

    @Test
    public void testGetEventWithTicketCategories() throws ParseException, ServerException {
        Event event = eventsLogic.getEventWithTicketCategories(2,false , generateDate(2));
        assertEquals(2,event.getTicketCategories().size());
    }

    @Test (expected = EventDoesNotExistServerException.class)
    public void testGetEventWithTicketCategoriesWithNotExistingEvent() throws ServerException {
       eventsLogic.getEventWithTicketCategories(99,true);
    }
}
