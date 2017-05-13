package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.coatChecks.CoatChecksRepository;
import dal.coatChecks.CoatChecksRepositoryMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.coatChecks.CoatChecksLogic;
import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static play.inject.Bindings.bind;

/**
 * Created by marco on 06.05.2017.
 */
public class CoatChecksLogicTest extends WithApplication{

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(CoatChecksRepository.class).to(CoatChecksRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class))
                .build();
    }

    /*@Test
    public void testHandoverAndFetchJacket() {
        final CoatChecksLogic coatChecksLogic = this.app.injector().instanceOf(CoatChecksLogic.class);
        CoatHanger expectedCoatHanger = new CoatHanger(24, 2, new Location());
        CoatCheck coatCheck = coatChecksLogic.createNewCoatCheck(new Integer(1), new Integer(24), );

        final CoatHanger resultingCoatHanger = coatChecksLogic.fetchJacket(new Date(), coatCheck.getId());

        assertEquals(expectedCoatHanger, resultingCoatHanger);
    }*/

    /*
    @Test
    public void testGetAlreadyFetchedJacket() {
        final CoatChecksLogic coatChecksLogic = this.app.injector().instanceOf(CoatChecksLogic.class);
        CoatHanger expectedCoatHanger = new CoatHanger(1, new Location());
        CoatCheck coatCheck = coatChecksLogic.createNewCoatCheck(2, 1, );

        final CoatHanger firstFetch = coatChecksLogic.fetchJacket(new Date(), coatCheck.getId());
        final CoatHanger secondFetch = coatChecksLogic.fetchJacket(new Date(), coatCheck.getId());

        assertEquals(expectedCoatHanger, firstFetch);
        assertNull(secondFetch);
    }*/

}
