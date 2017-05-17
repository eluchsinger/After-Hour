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
import logic.users.UsersLogic;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistException;
import models.exceptions.UserHasNoTicketException;
import models.tickets.Ticket;
import models.users.User;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static play.inject.Bindings.bind;

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
    public void testGetExistingUserById() throws UserDoesNotExistException {
        final User expectedUser = usersLogic.getUserById(1);
        assertEquals(new Integer(1),expectedUser.getId());
    }

    @Test (expected = UserDoesNotExistException.class)
    public void testGetNonExistingUserById() throws UserDoesNotExistException {
        final User expectedUser = usersLogic.getUserById(123123123);
    }

    @Test
    public void testUserGeneratingId() throws UserDoesNotExistException {
        final User user = usersLogic.getUserById(2);
        assertEquals(user.getId(), new Integer(2));
    }

    @Test
    public void testGetTicket() throws ServerException {
        Ticket ticket = usersLogic.getTicket(1,1);
        assertNotNull(ticket);
    }

    @Test (expected = UserHasNoTicketException.class)
    public void testGetTicketUserHasNoTicket() throws ServerException {
        usersLogic.getTicket(1,100);
    }

    @Test  (expected = UserDoesNotExistException.class)
    public void testGetTicketUserDoesNotExist() throws ServerException {
        usersLogic.getTicket(100,1);
    }

    @Test
    public void testLogin() throws ServerException {
        User user = usersLogic.login("silvio.berlusconi@italy.it", "123456");
        assertEquals("silvio.berlusconi@italy.it", user.getEmail());
    }

    @Test
    public void testGetUserByEmail() throws UserDoesNotExistException {
        User user = usersLogic.getUserByEmail("silvio.berlusconi@italy.it");
        assertEquals("Silvio",user.getFirstName());
    }

    @Test (expected = UserDoesNotExistException.class)
    public void testGetUserByMailWithNotExistingUser() throws UserDoesNotExistException {
        usersLogic.getUserByEmail("donald.trump@uso.com");
    }
}
