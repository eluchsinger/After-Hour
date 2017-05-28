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
import logic.users.UsersLogic;
import models.events.Event;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistServerException;
import models.exceptions.UserHasNoTicketServerException;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.ParseException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static play.inject.Bindings.bind;
import static utils.DateGenerator.generateDate;

/**
 * Created by Fabian Schwyter on 03.04.17.
 * Tests the logic of the "Users"-Domain.
 * To test logic-only, the database is mocked.
 */
public class UsersLogicTest extends WithApplication {
    private UsersLogic usersLogic;

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
        usersLogic = this.app.injector().instanceOf(UsersLogic.class);
    }

    @Test
    public void testGetExistingUserById() throws UserDoesNotExistServerException {
        final User expectedUser = usersLogic.getUserById(1);
        assertEquals(1,expectedUser.getId().intValue());
    }

    @Test (expected = UserDoesNotExistServerException.class)
    public void testGetNonExistingUserById() throws UserDoesNotExistServerException {
        usersLogic.getUserById(123123123);
    }

    @Test
    public void testUserGeneratingId() throws UserDoesNotExistServerException {
        final User user = usersLogic.getUserById(2);
        assertEquals(2,user.getId().intValue());
    }

    @Test
    public void testGetTicket() throws ServerException {
        Ticket ticket = usersLogic.getTicket(1,1);
        assertNotNull(ticket);
    }

    @Test (expected = UserHasNoTicketServerException.class)
    public void testGetTicketUserHasNoTicket() throws ServerException {
        usersLogic.getTicket(1,100);
    }

    @Test  (expected = UserDoesNotExistServerException.class)
    public void testGetTicketUserDoesNotExist() throws ServerException {
        usersLogic.getTicket(100,1);
    }

    @Test
    public void testLogin() throws ServerException {
        User user = usersLogic.login("silvio.berlusconi@italy.it", "123456");
        assertEquals("silvio.berlusconi@italy.it", user.getEmail());
    }

    @Test
    public void testUserReturnedWithLoginHasValidCoatChecksOnly() throws ServerException {
        User user = usersLogic.login("silvio.berlusconi@italy.it", "123456");
        List<CoatCheck> coatChecks = user.getCoatChecks();
        assertEquals(3, coatChecks.size());
    }

    @Test
    public void testGetUserByEmail() throws UserDoesNotExistServerException {
        User user = usersLogic.getUserByEmail("silvio.berlusconi@italy.it");
        assertEquals("Silvio",user.getFirstName());
    }

    @Test (expected = UserDoesNotExistServerException.class)
    public void testGetUserByMailWithNotExistingUser() throws UserDoesNotExistServerException {
        usersLogic.getUserByEmail("donald.trump@uso.com");
    }

    @Test
    public void testGetAvailableEvents() throws ParseException, UserDoesNotExistServerException {
        List<Event> events = usersLogic.getEventsAvailable(1, generateDate(0));
        assertEquals(4, events.size());

        events = usersLogic.getEventsAvailable(1, generateDate(200));
        assertEquals(0, events.size());
    }
}
