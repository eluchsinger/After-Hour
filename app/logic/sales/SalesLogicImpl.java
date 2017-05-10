package logic.sales;

import dal.tickets.TicketRepository;
import dal.users.UsersRepository;
import models.events.TicketCategory;
import models.tickets.Ticket;
import models.users.User;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Fabian on 08.04.17.
 * Handles the whole sales problem domain.
 * (Buying tickets, drinks, etc.)
 */
public class SalesLogicImpl implements SalesLogic{

    private final TicketRepository ticketRepository;
    private final UsersRepository usersRepository;

    @Inject
    public SalesLogicImpl(final TicketRepository ticketRepository,
                          final UsersRepository usersRepository) {
        this.ticketRepository = ticketRepository;
        this.usersRepository = usersRepository;
    }

    /**
     * Buys a ticket for the user and for the ticket category.
     *
     * @param userId           The User ID of the user buying the ticket.
     * @param ticketCategoryId The category of the ticket to buy. (The category is linked to the event)
     * @return Returns the newly created ticket.
     */
    @Override
    public Ticket buyTicket(final Integer userId, final Integer ticketCategoryId) {
        TicketCategory ticketCategory = this.ticketRepository
                .getTicketCategoryById(ticketCategoryId);

        User buyingUser = this.usersRepository.getUserById(userId);
        Ticket soldTicket = ticketCategory.sellTicket(buyingUser);

        if (validateUser(buyingUser) && validateTicket(ticketCategory) && validateTicketBought(ticketCategoryId, userId)){
            this.ticketRepository.persistTicket(soldTicket);

            return soldTicket;
        }
        return null;
    }

    public boolean validateUser(final User buyingUser){
        return buyingUser != null;
    }

    public boolean validateTicket(final TicketCategory ticketCategory){
        return ticketCategory != null
                && ticketCategory.getEndAvailability().before(new Date())
                && ticketCategory.getStartAvailability().after(new Date());
    }

    public boolean validateTicketBought(final Integer ticketCategoryId, final Integer userId){
        Optional<Ticket> optionalTicket = this.usersRepository.getUserById(1).getTickets().stream()
                .filter(x -> x.getTicketCategory().getId().equals(ticketCategoryId))
                .findFirst();
        if (!optionalTicket.isPresent()){
            return true;
        } else {
            return false;
        }
    }

}
