package dal.tickets;

import models.events.TicketCategory;
import models.tickets.Ticket;

import java.util.List;

/**
 * Created by Fabian Schwyter on 17.04.2017.
 * Handles the ticket operations using a mocked datasource.
 */
public class TicketRepositoryMock implements TicketRepository {
    private List<TicketCategory> ticketCategories;
    private List<Ticket> tickets;

    @Override
    public void registerTicketCategory(TicketCategory ticketCategory) {
        ticketCategories.add(ticketCategory);
    }

    @Override
    public TicketCategory getTicketCategoryById(Integer ticketCategoryId) {
        return ticketCategories
                .stream()
                .filter(ticketCategory -> ticketCategory.getId() == ticketCategoryId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void persistTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
