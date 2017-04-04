import dal.UsersRepository;
import dal.mocks.UsersRepositoryMock;
import domain.UserDomain;
import models.users.User;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.Injector;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceInjectorBuilder;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.inject.Bindings.bind;

/**
 * Created by Fabian Schwyter on 03.04.17.
 */
public class UserDomainTest extends WithApplication {
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
        UserDomain domain = this.injector.instanceOf(UserDomain.class);
        User expectedUser = domain.getUserById(1);
        assertNotNull(expectedUser);
    }

    @Test
    public void testGetNonExistingUserById() {
        UserDomain domain = this.injector.instanceOf(UserDomain.class);
        User expectedUser = domain.getUserById(123123123);
        assertNull(expectedUser);
    }
}
