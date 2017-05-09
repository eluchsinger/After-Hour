package dal.tickets;

import models.events.TicketCategory;
import models.tickets.Ticket;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        if(ticketCategory.getId() == null) {
            Optional<TicketCategory> ticketCategoryMaxId = this.ticketCategories
                    .stream()
                    .max(Comparator.comparingInt(TicketCategory::getId));

            if(ticketCategoryMaxId.isPresent())
                ticketCategory.setId(ticketCategoryMaxId.get().getId() + 1);
            else
                ticketCategory.setId(1);
        }
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
