package logic.sales;

import models.events.TicketCategory;

/**
 * Created by Fabian on 08.04.17.
 */
public interface SalesLogic {
    boolean buyTicket(Integer userId, Integer ticketCategoryId);
}
