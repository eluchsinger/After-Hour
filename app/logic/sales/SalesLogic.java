package logic.sales;

import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistException;
import models.tickets.Ticket;

import java.util.Date;

/**
 * Created by Fabian on 08.04.17.
 * Handles the whole sales problem domain.
 * (Buying tickets, drinks, etc.)
 */
public interface SalesLogic {
    /**
     * Buys a ticket for the {@link models.users.User} and for the {@link models.events.TicketCategory}.
     * @param userId The unique User ID of the user buying the ticket.
     * @param ticketCategoryId The unique category ID of the ticket to buy. (The category is linked to the event)
     * @return Returns the newly created ticket.
     */
    Ticket buyTicket(Integer userId, Integer ticketCategoryId) throws ServerException;

    Ticket buyTicket(Integer userId, Integer ticketCategoryId, Date date) throws ServerException;
}
