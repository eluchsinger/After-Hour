package integration.database;

import dal.events.EventsRepositoryJPA;
import models.events.Event;
import models.events.Location;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Fabian Schwyter on 07.04.2017.
 * Tests the persistence of the events using the Database.
 */
public class EventPersistenceTesting extends WithApplication {

    @Test
    public void testRegisterNewEvent(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final EventsRepositoryJPA repository = new EventsRepositoryJPA(jpaApi);
            final Event expectedEvent = new Event(null,"Disco Disco,Party Party", "Party der 90iger, mit DJ Abu Fabi und dem Kimmono Girls");
            repository.registerEvent(expectedEvent);

            final Event actualEvent = repository.getEventById(expectedEvent.getId());

            assertEquals(expectedEvent, actualEvent);
        });
    }

    @Test
    public void testGetLocationByName(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final EventsRepositoryJPA repository = new EventsRepositoryJPA(jpaApi);

            final Location retreivedLocation = repository.getLocationByName("Plaza");

            assertEquals("Plaza", retreivedLocation.getName());
        });
    }

}
