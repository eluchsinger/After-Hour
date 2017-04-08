package integration.database;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.sun.media.jfxmedia.logging.Logger;
import dal.users.UsersRepository;
import dal.users.UsersRepositoryJPA;
import models.users.Gender;
import models.users.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.db.jpa.JPA;
import play.db.jpa.JPAApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;
import play.test.WithApplication;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 */
public class UserPersistenceTesting extends WithApplication {
    @Inject
    private JPAApi jpaApi;

    @Before
    public void setup() {
        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
                .builder(new ApplicationLoader.Context(Environment.simple()));
        Guice.createInjector(builder.applicationModule()).injectMembers(this);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testRegisterNewUser() {
        this.jpaApi.withTransaction(() -> {
            UsersRepositoryJPA repository = new UsersRepositoryJPA(jpaApi);
            Date creationDate = new Date();
            User expectedUser = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
            repository.registerUser(expectedUser);

            User actualUser = repository.getUserById(expectedUser.getId());

            assertEquals(expectedUser, actualUser);
        });
    }

    @Test
    public void testResetUsersRepository() {
        this.jpaApi.withTransaction(() -> {
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
    }

    /**
     * This test tests that the tables were dropped before.
     */
    @Test
    public void testUserIDsStartFrom1() {
        this.jpaApi.withTransaction(() -> {
            UsersRepository repository = new UsersRepositoryJPA(jpaApi);
            Date creationDate = new Date();

            // Add new users
            User newUser1 = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
            repository.registerUser(newUser1);
            User actualUser = repository.getUserById(1);
            assertEquals(newUser1, actualUser);
        });
    }
}
