package integration.database;

import dal.users.UsersRepository;
import dal.users.UsersRepositoryJPA;
import models.users.Gender;
import models.users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.db.jpa.JPAApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 */
public class UserPersistenceTesting extends WithApplication {
    private Application application;

    @Before
    public void setup() {
        this.application = new GuiceApplicationBuilder()
                .build();
    }

    @After
    public void teardown() {
    }

    @Test
    public void testRegisterNewUser() {
        Helpers.running(this.application, () -> {
            final JPAApi jpaApi = this.application.injector().instanceOf(JPAApi.class);
            jpaApi.withTransaction(() -> {
                UsersRepositoryJPA repository = new UsersRepositoryJPA(jpaApi);
                Date creationDate = new Date();
                User expectedUser = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
                repository.registerUser(expectedUser);

                User actualUser = repository.getUserById(expectedUser.getId());

                assertEquals(expectedUser, actualUser);
            });
        });
    }

    @Test
    public void testResetUsersRepository() {
        Helpers.running(this.application, () -> {
            final JPAApi jpaApi = this.application.injector().instanceOf(JPAApi.class);
            jpaApi.withTransaction(() -> {
                UsersRepository repository = new UsersRepositoryJPA(jpaApi);
                Date creationDate = new Date();

                // Add new users
                User newUser1 = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
                User newUser2 = new User(null, "fabienne.ebis@hsr.ch", "Ebis", "Fabienne", creationDate, Gender.FEMALE);
                repository.registerUser(newUser1);
                repository.registerUser(newUser2);

                repository.resetRepository();
                assertEquals(0, repository.countUsers());
            });
        });
    }

    @Test
    public void testGetUserByEmail() {
        Helpers.running(this.application, () -> {
            final JPAApi jpaApi = this.application.injector().instanceOf(JPAApi.class);
            jpaApi.withTransaction(() -> {
                UsersRepository repository = new UsersRepositoryJPA(jpaApi);
                Date creationDate = new Date();

                // Add new users
                User user = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
                repository.registerUser(user);
                User userActual = repository.getUserByEmail("max.muster@hsr.ch");

                assertEquals(user, userActual);

            });
        });
    }
}
