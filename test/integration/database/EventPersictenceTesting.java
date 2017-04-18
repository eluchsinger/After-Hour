package integration.database;

import com.google.inject.Guice;
import dal.events.EventsRepository;
import dal.events.EventsRepositoryJPA;
import models.events.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Environment;
import play.api.ApplicationLoader;
import play.db.jpa.JPAApi;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.WithApplication;

import javax.inject.Inject;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Fabian on 07.04.2017.
 */
public class EventPersictenceTesting extends WithApplication {
    @Inject
    private  JPAApi jpaApi;

    @Before
    public void setup() {
        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
                .builder(new play.ApplicationLoader.Context(Environment.simple()));
        Guice.createInjector(builder.applicationModule()).injectMembers(this);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testRegisterNewEvent(){
        this.jpaApi.withTransaction(() -> {
            EventsRepositoryJPA repository = new EventsRepositoryJPA(jpaApi);
            Event expectedEvent = new Event(null,"Disco Disco,Party Party", "Party der 90iger, mit DJ Abu Fabi und dem Kimmono Girls");
            play.Logger.info("Created Event: " + expectedEvent);
            repository.registerEvent(expectedEvent);

            Event actualEvent = repository.getEventById(expectedEvent.getId());

            assertEquals(expectedEvent, actualEvent);
        });
    }

}
