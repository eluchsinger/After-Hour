import dal.UsersRepository;
import dal.mocks.UsersRepositoryMock;
import logic.users.UsersLogic;
import models.users.User;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static play.test.Helpers.route;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian Schwyter on 03.04.17.
 */
public class UsersLogicTest extends WithApplication {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    private Application application;
    private Injector injector;

    @Before
    public void initalize(){
        this.application = new GuiceApplicationBuilder()
                .overrides(bind(UsersRepository.class).to(UsersRepositoryMock.class))
                .build();
        this.injector = application.injector();
    }

    @Test
    public void testGetExistingUserById() {
        UsersLogic domain = this.injector.instanceOf(UsersLogic.class);
        User expectedUser = domain.getUserById(1);
        assertNotNull(expectedUser);
    }

    @Test
    public void testGetNonExistingUserById() {
        UsersLogic domain = this.injector.instanceOf(UsersLogic.class);
        User expectedUser = domain.getUserById(123123123);
        assertNull(expectedUser);
    }
}
