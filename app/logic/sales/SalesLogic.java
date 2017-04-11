package logic.sales;

import models.tickets.Ticket;

/**
 * Created by Fabian on 08.04.17.
 */
public interface SalesLogic {
    /**
     * Buys a ticket for the user and for the ticket category.
     * @param userId The User ID of the user buying the ticket.
     * @param ticketCategoryId The category of the ticket to buy. (The category is linked to the event)
     * @return Returns the newly created ticket.
     */
    Ticket buyTicket(Integer userId, Integer ticketCategoryId);
}
