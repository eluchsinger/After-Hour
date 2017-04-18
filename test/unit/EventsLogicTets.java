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
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian on 08.04.17.
 */

public class EventsLogicTets extends WithApplication {
    private Application application;
    private Injector injector;

    @Before
    public void initalize(){
        this.application = new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class))
                .build();
        this.injector = application.injector();
    }

    @Test
    public void testGetExistingEvent(){
        EventsLogic domain = this.injector.instanceOf(EventsLogic.class);
        Event event= domain.getEventById(1);


        assertEquals(new Integer (1), event.getId());
        //Todo: Make Test with TestData.
    }
}
