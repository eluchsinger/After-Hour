package dal.ticket_categories;

import models.events.TicketCategory;

/**
 * Created by Fabian on 09.04.2017.
 */
public interface TicketCategoriesRepository {
    TicketCategory registerTicketCategory(TicketCategory ticketCategory);
    TicketCategory getTicketCategoryById(Integer ticketCategoryId);
}
