package integration.database;

import dal.users.UsersRepository;
import dal.users.UsersRepositoryJPA;
import models.users.Gender;
import models.users.User;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 * Tests the persistence of the Users into the Database.
 */
public class UserPersistenceTesting extends WithApplication {

    @Test
    public void testRegisterNewUser() {
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final UsersRepositoryJPA repository = new UsersRepositoryJPA(jpaApi);
            final Date creationDate = new Date();
            final User expectedUser = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
            repository.registerUser(expectedUser);

            final User actualUser = repository.getUserById(expectedUser.getId());

            assertEquals(expectedUser, actualUser);
        });
    }

    @Test
    public void testResetUsersRepository() {
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final UsersRepository repository = new UsersRepositoryJPA(jpaApi);
            final Date creationDate = new Date();

            // Add new users
            final User newUser1 = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
            final User newUser2 = new User(null, "fabienne.ebis@hsr.ch", "Ebis", "Fabienne", creationDate, Gender.FEMALE);
            repository.registerUser(newUser1);
            repository.registerUser(newUser2);

            repository.resetRepository();
            assertEquals(0, repository.countUsers());
        });
    }

    @Test
    public void testGetUserByEmail() {
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final UsersRepository repository = new UsersRepositoryJPA(jpaApi);
            final Date creationDate = new Date();

            // Add new users
            final User user = new User(null, "max.muster@hsr.ch", "Muster", "Max", creationDate, Gender.MALE);
            repository.registerUser(user);
            final User userActual = repository.getUserByEmail("max.muster@hsr.ch");

            assertEquals(user, userActual);
        });
    }

    @Test
    public void testGetUserByEmailNonExistingUser(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final UsersRepository repository = new UsersRepositoryJPA(jpaApi);
            final User userActual = repository.getUserByEmail("fab.schwyter@gmail.ch");

            assertNull(userActual);
        });
    }

    @Test
    public void testVitaliIsEmployee() {
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final UsersRepository repository = new UsersRepositoryJPA(jpaApi);

            final User vitali = repository.getUserByEmail("vitali.klitschko@kauf.ch");

            assertTrue(vitali.isEmployee());
        });
    }
}
