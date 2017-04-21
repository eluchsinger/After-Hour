package integration.database;

import dal.events.EventsRepositoryJPA;
import models.events.Event;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Fabian on 07.04.2017.
 */
public class EventPersictenceTesting extends WithApplication {

    @Before
    public void setup() {

    }

    @Test
    public void testRegisterNewEvent(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            EventsRepositoryJPA repository = new EventsRepositoryJPA(jpaApi);
            Event expectedEvent = new Event(null,"Disco Disco,Party Party", "Party der 90iger, mit DJ Abu Fabi und dem Kimmono Girls");
            play.Logger.info("Created Event: " + expectedEvent);
            repository.registerEvent(expectedEvent);

            Event actualEvent = repository.getEventById(expectedEvent.getId());

            assertEquals(expectedEvent, actualEvent);
        });
    }

}
