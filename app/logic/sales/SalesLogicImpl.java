package logic.sales;

import dal.ticket_categories.TicketCategoriesRepository;
import dal.users.UsersRepository;
import models.events.TicketCategory;
import models.tickets.Ticket;
import models.users.User;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

/**
 * Created by Fabian on 08.04.17.
 */
public class SalesLogicImpl implements SalesLogic{

    private TicketCategoriesRepository ticketCategoriesRepository;
    private UsersRepository usersRepository;
    private JPAApi jpaApi;

    @Inject
    public SalesLogicImpl(JPAApi jpaApi, final TicketCategoriesRepository ticketCategoriesRepository,
                          final UsersRepository usersRepository) {
        this.ticketCategoriesRepository = ticketCategoriesRepository;
        this.usersRepository = usersRepository;
        this.jpaApi = jpaApi;
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
        TicketCategory ticketCategory = this.ticketCategoriesRepository
                .getTicketCategoryById(ticketCategoryId);
        User buyingUser = this.usersRepository.getUserById(userId);
        Ticket soldTicket = ticketCategory.sellTicket(buyingUser);
        this.ticketCategoriesRepository.persistTicket(soldTicket);

        return soldTicket;
    }
}
