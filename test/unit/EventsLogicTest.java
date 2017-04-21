package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketMock;
import dal.tickets.TicketRepository;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.events.EventsLogic;
import models.events.Event;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian on 08.04.17.
 */

public class EventsLogicTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class))
                .build();
    }

    @Test
    public void testGetExistingEvent(){
        EventsLogic domain = this.app.injector().instanceOf(EventsLogic.class);
        Event event = domain.getEventById(2);
        assertEquals(new Integer (2), event.getId());
    }
}
