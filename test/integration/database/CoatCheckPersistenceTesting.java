package integration.database;

import dal.coatChecks.CoatChecksRepositoryJPA;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryJPA;
import dal.users.UsersRepositoryJPA;
import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;
import sun.security.ssl.Debug;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatCheckPersistenceTesting extends WithApplication{

    @Test
    public void testGetCoatHangerByLocationAndNumber(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final CoatChecksRepositoryJPA repository = new CoatChecksRepositoryJPA(jpaApi);
            final EventsRepositoryJPA eveventsRepository = new EventsRepositoryJPA(jpaApi);

            Location location = eveventsRepository.getLocationByName("Plaza");
            CoatHanger coatHanger = new CoatHanger(17, location);
            repository.persistNewCoatHanger(coatHanger);

            final CoatHanger resultingCoatHanger = repository.getCoatHangerByNumberAndLocationID(coatHanger.getCoatHangerNumber(), location.getPlaceId());

            assertEquals(coatHanger, resultingCoatHanger);
        });
    }

    @Test
    public void testGetCoatHangerByNumberAndLocationIDWithoutGeneratedData(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final CoatChecksRepositoryJPA repository = new CoatChecksRepositoryJPA(jpaApi);
            final EventsRepositoryJPA eventsRepositoryJPA = new EventsRepositoryJPA(jpaApi);

            Location location = eventsRepositoryJPA.getLocationByName("Plaza");
            CoatHanger expectedCoatHanger = new CoatHanger(null, 50, location);
            repository.persistNewCoatHanger(expectedCoatHanger);

            CoatHanger coatHanger = repository.getCoatHangerByNumberAndLocationID(new Integer(50), location.getPlaceId());

            assertEquals(expectedCoatHanger.getCoatHangerNumber(), coatHanger.getCoatHangerNumber());
            assertEquals(location.getPlaceId(), coatHanger.getLocation().getPlaceId());
        });
    }

    @Test
    public void testGetCoatHangerByNumberAndLocationIDWithGeneratedData(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final CoatChecksRepositoryJPA repository = new CoatChecksRepositoryJPA(jpaApi);

            CoatHanger coatHanger = repository.getCoatHangerByNumberAndLocationID(new Integer(5), "ChIJIXJ33hsKkEcRTTvRa3eNxd0");

            assertEquals(new Integer(5), coatHanger.getCoatHangerNumber());
            assertEquals("ChIJIXJ33hsKkEcRTTvRa3eNxd0", coatHanger.getLocation().getPlaceId());
        });
    }

    @Test
    public void testCreateNewCoatCheck(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final CoatChecksRepositoryJPA repository = new CoatChecksRepositoryJPA(jpaApi);
            final EventsRepositoryJPA eventsRepositoryJPA = new EventsRepositoryJPA(jpaApi);
            final UsersRepositoryJPA usersRepositoryJPA = new UsersRepositoryJPA(jpaApi);

            User user = usersRepositoryJPA.getUserByEmail("silvio.berlusconi@italy.it");
            Location location = eventsRepositoryJPA.getLocationByName("Plaza");

            CoatCheck coatCheck = repository.createNewCoatCheck(user, location, new Date(), new Integer(4));

            assertEquals(user.getEmail(), coatCheck.getUser().getEmail());
            assertEquals(new Integer(4), coatCheck.getCoatHanger().getCoatHangerNumber());
        });
    }

    @Test
    public void testFetchJacket(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final CoatChecksRepositoryJPA repository = new CoatChecksRepositoryJPA(jpaApi);
            final EventsRepositoryJPA eventsRepositoryJPA = new EventsRepositoryJPA(jpaApi);
            final UsersRepositoryJPA usersRepositoryJPA = new UsersRepositoryJPA(jpaApi);

            User user = usersRepositoryJPA.getUserByEmail("franz.becki@idc.yolo");
            Location location = eventsRepositoryJPA.getLocationByName("Plaza");

            final Integer coatHangerNumber = new Integer(3);
            CoatCheck coatCheck = repository.createNewCoatCheck(user, location, new Date(), coatHangerNumber);

            CoatHanger coatHanger = repository.fetchJacket(new Date(), coatCheck.getPublicIdentifier());

            System.out.print("public ID:");
            System.out.print(coatCheck.getPublicIdentifier());

            assertEquals(location.getPlaceId(), coatHanger.getLocation().getPlaceId());
            assertEquals(coatHangerNumber, coatHanger.getCoatHangerNumber());
            assertTrue(repository.getCoatCheckByPublicIdentifier(coatCheck.getPublicIdentifier()) != null);
        });
    }
}

