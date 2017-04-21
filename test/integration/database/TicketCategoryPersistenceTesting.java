package integration.database;

import dal.events.EventsRepositoryJPA;
import dal.tickets.TicketRepositoryJPA;
import models.events.Event;
import models.events.TicketCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Fabian on 09.04.2017.
 */
public class TicketCategoryPersistenceTesting extends WithApplication {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
    }

    @Test
    public void testRegisterNewTicketCategoryWithoutEvent(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            TicketRepositoryJPA repository = new TicketRepositoryJPA(jpaApi);
            try {
                TicketCategory expectedTicketCategory = new TicketCategory(null, "Studenten Ticket", "Ticket für Studenten", null, 15.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20") );
                play.Logger.info("Created TicketCategory: " + expectedTicketCategory);
                repository.registerTicketCategory(expectedTicketCategory);
                TicketCategory actualTicketCategory = repository.getTicketCategoryById(expectedTicketCategory.getId());
                assertEquals(expectedTicketCategory.getId(), actualTicketCategory.getId());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testReferenceFromTicketCategoryToEvent(){
        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            TicketRepositoryJPA ticketCategoriesRepositoryJPA = new TicketRepositoryJPA(jpaApi);
            EventsRepositoryJPA eventsRepositoryJPA = new EventsRepositoryJPA(jpaApi);
            try {
                Event event = eventsRepositoryJPA.getEventById(1);
                TicketCategory expectedTicketCategory = new TicketCategory(null, "Studenten Ticket", "Ticket für Studenten", event , 15.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20") );
                play.Logger.info("Created TicketCategory: " + expectedTicketCategory + " for Event:" + event);
                ticketCategoriesRepositoryJPA.registerTicketCategory(expectedTicketCategory);
                assertTrue(eventsRepositoryJPA.getEventById(1).getTicketCategories().contains(expectedTicketCategory));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
