package logic.sales;

import models.events.TicketCategory;
import models.tickets.Ticket;

/**
 * Created by Fabian on 08.04.17.
 */
public interface SalesLogic {
    Ticket buyTicket(Integer userId, Integer ticketCategoryId);
}
