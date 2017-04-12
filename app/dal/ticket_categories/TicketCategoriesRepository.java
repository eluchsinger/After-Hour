package dal.ticket_categories;

import models.events.TicketCategory;
import models.tickets.Ticket;

/**
 * Created by Fabian on 09.04.2017.
 */
public interface TicketCategoriesRepository {
    TicketCategory registerTicketCategory(TicketCategory ticketCategory);
    TicketCategory getTicketCategoryById(Integer ticketCategoryId);

    /**
     * This method saves the created ticket into the database.
     * Should be called to persist NEWLY created tickets to the database.
     * @param ticket The ticket to save into the DB
     * @return Returns the ticket added to the DB.
     */
    Ticket persistTicket(Ticket ticket);
}
