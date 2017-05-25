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
    private int ticketCategoryIdCounter;

    public TicketRepositoryMock() {
        this.ticketCategories = new ArrayList<>();
        this.tickets = new ArrayList<>();
        ticketCategoryIdCounter = 1;
    }

    @Override
    public void registerTicketCategory(TicketCategory ticketCategory) {
        ticketCategory.setId(ticketCategoryIdCounter++);
        ticketCategories.add(ticketCategory);
        ticketCategories.add(ticketCategory);
    }

    @Override
    public TicketCategory getTicketCategoryById(final Integer ticketCategoryId) {
        return this.ticketCategories
                .stream()
                .filter(ticketCategory -> ticketCategory.getId().equals(ticketCategoryId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void persistTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
