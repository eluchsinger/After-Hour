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
import logic.coatchecks.CoatChecksLogic;
import models.tickets.CoatCheck;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.*;
import static play.inject.Bindings.bind;

public class CoatChecksLogicTest extends WithApplication{

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

    @Test
    public void testHandoverAndFetchJacket() {
        final CoatChecksLogic coatChecksLogic = this.app.injector().instanceOf(CoatChecksLogic.class);
        CoatCheck coatCheck = coatChecksLogic.createNewCoatCheck("silvio.berlusconi@italy.it", 2, "Plaza");

        assertNotNull(coatCheck);
        assertEquals(2, coatCheck.getCoatHanger().getCoatHangerNumber().intValue());
        assertEquals("silvio.berlusconi@italy.it", coatCheck.getUser().getEmail());
    }

    @Test
    public void testGetAlreadyFetchedJacket() {
        final CoatChecksLogic coatChecksLogic = this.app.injector().instanceOf(CoatChecksLogic.class);
        CoatCheck coatCheck = coatChecksLogic.createNewCoatCheck("silvio.berlusconi@italy.it", 2, "Plaza");

        assertTrue(coatChecksLogic.fetchJacket(new Date(), coatCheck.getPublicIdentifier()));
        assertFalse(coatChecksLogic.fetchJacket(new Date(), coatCheck.getPublicIdentifier()));
    }

}
