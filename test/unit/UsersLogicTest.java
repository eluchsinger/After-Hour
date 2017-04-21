package unit;

import config.StartupConfiguration;
import config.StartupConfigurationMock;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryMock;
import dal.tickets.TicketMock;
import dal.tickets.TicketRepository;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryMock;
import logic.users.UsersLogic;
import models.users.User;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.SimpleDateFormat;

import static junit.framework.Assert.*;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian Schwyter on 03.04.17.
 */
public class UsersLogicTest extends WithApplication {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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
    public void testGetExistingUserById() {
        UsersLogic domain = this.app.injector().instanceOf(UsersLogic.class);
        User expectedUser = domain.getUserById(1);
        assertNotNull(expectedUser);
    }

    @Test
    public void testGetNonExistingUserById() {
        UsersLogic domain = this.app.injector().instanceOf(UsersLogic.class);
        User expectedUser = domain.getUserById(123123123);
        assertNull(expectedUser);
    }

    @Test
    public void testUserGeneratingId(){
        UsersLogic domain = this.app.injector().instanceOf(UsersLogic.class);
        User user = domain.getUserById(2);
        assertEquals(user.getId(), new Integer(2));
    }
}
