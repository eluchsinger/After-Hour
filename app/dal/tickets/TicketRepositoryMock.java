package dal.tickets;

import models.events.TicketCategory;
import models.tickets.Ticket;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian Schwyter on 17.04.2017.
 * Handles the ticket operations using a mocked datasource.
 */
@Singleton
public class TicketRepositoryMock implements TicketRepository {
    private List<TicketCategory> ticketCategories;
    private List<Ticket> tickets;

    public TicketRepositoryMock() {
        this.ticketCategories = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

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
