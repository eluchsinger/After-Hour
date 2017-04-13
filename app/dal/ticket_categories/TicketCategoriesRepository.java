package dal.ticket_categories;

import models.events.TicketCategory;
import models.tickets.Ticket;

/**
 * Created by Fabian Schwyter on 09.04.2017.
 * Handles the ticketing operations.
 */
public interface TicketCategoriesRepository {

    /**
     * Adds a new {@link TicketCategory} to the repository.
     * @param ticketCategory The new {@link TicketCategory} to be added.
     */
    void registerTicketCategory(TicketCategory ticketCategory);

    /**
     * Finds and returns a {@link TicketCategory} using a unique ID.
     * @param ticketCategoryId The unique ID of the {@link TicketCategory}
     * @return Returns the found {@link TicketCategory} or null, if nothing was found.
     */
    TicketCategory getTicketCategoryById(Integer ticketCategoryId);

    /**
     * This method saves a {@link Ticket} into the Repository.
     * Should be called to persist a NEWLY created {@link Ticket} into the repository.
     * @param ticket The {@link Ticket} to save into the DB
     */
    void persistTicket(Ticket ticket);
}
