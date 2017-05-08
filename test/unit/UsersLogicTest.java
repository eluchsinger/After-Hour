package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketRepository;
import dal.tickets.TicketRepositoryMock;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.users.UsersLogic;
import models.users.User;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static junit.framework.Assert.*;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian Schwyter on 03.04.17.
 * Tests the logic of the "Users"-Domain.
 * To test logic-only, the database is mocked.
 */
public class UsersLogicTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .overrides(bind(EventsRepository.class).to(EventsRepositoryMock.class))
                .overrides(bind(TicketRepository.class).to(TicketRepositoryMock.class))
                .overrides(bind(StartupConfiguration.class).to(StartupConfigurationMock.class))
                .build();
    }

    @Test
    public void testGetExistingUserById() {
        final UsersLogic usersLogic = this.app.injector().instanceOf(UsersLogic.class);
        final User expectedUser = usersLogic.getUserById(1);
        assertNotNull(expectedUser);
    }

    @Test
    public void testGetNonExistingUserById() {
        final UsersLogic usersLogic = this.app.injector().instanceOf(UsersLogic.class);
        final User expectedUser = usersLogic.getUserById(123123123);
        assertNull(expectedUser);
    }

    @Test
    public void testUserGeneratingId(){
        final UsersLogic usersLogic = this.app.injector().instanceOf(UsersLogic.class);
        final User user = usersLogic.getUserById(2);
        assertEquals(user.getId(), new Integer(2));
    }

    @Test
    public void testGetAllCoatChecks() {
        throw new NotImplementedException();
    }
}
