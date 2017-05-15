package integration.database;

import dal.events.EventsRepositoryJPA;
import dal.tickets.TicketRepositoryJPA;
import models.events.Event;
import models.events.TicketCategory;
import org.junit.Test;
import play.db.jpa.JPAApi;
import play.test.WithApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Fabian Schwyter on 09.04.2017.
 * Tests the persistence of the TicketCategories using the Database.
 */
public class TicketCategoryPersistenceTesting extends WithApplication {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testRegisterNewTicketCategoryWithoutEvent() throws ParseException {
        final Date validFrom = this.dateFormat.parse("2017-4-20");
        final Date validUntil = this.dateFormat.parse("2017-5-20");

        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final TicketRepositoryJPA repository = new TicketRepositoryJPA(jpaApi);
            final TicketCategory expectedTicketCategory = new TicketCategory(null, "Studenten Ticket",
                    "Ticket für Studenten",
                    15.00,
                    validFrom,
                    validUntil);
            repository.registerTicketCategory(expectedTicketCategory);
            final TicketCategory actualTicketCategory = repository.getTicketCategoryById(expectedTicketCategory.getId());
            assertEquals(expectedTicketCategory.getId(), actualTicketCategory.getId());
        });
    }

    @Test
    public void testReferenceFromTicketCategoryToEvent() throws ParseException {
        final Date validFrom = this.dateFormat.parse("2017-4-20");
        final Date validUntil = this.dateFormat.parse("2017-5-20");

        final JPAApi jpaApi = this.app.injector().instanceOf(JPAApi.class);
        jpaApi.withTransaction(() -> {
            final TicketRepositoryJPA ticketCategoriesRepositoryJPA = new TicketRepositoryJPA(jpaApi);
            final EventsRepositoryJPA eventsRepositoryJPA = new EventsRepositoryJPA(jpaApi);
            final Event event = eventsRepositoryJPA.getEventById(1);
            final TicketCategory expectedTicketCategory = new TicketCategory(null, "Studenten Ticket",
                    "Ticket für Studenten",
                    event , 15.00,
                    validFrom,
                    validUntil);
            ticketCategoriesRepositoryJPA.registerTicketCategory(expectedTicketCategory);
            assertTrue(eventsRepositoryJPA.getEventById(1).getTicketCategories().contains(expectedTicketCategory));
        });
    }

}
