package dal.ticket_categories;

import models.events.TicketCategory;
import models.tickets.Ticket;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Fabian on 09.04.2017.
 */
public class TicketCategoriesRepositoryJPA implements TicketCategoriesRepository {
    public JPAApi jpaApi;

    @Inject
    public TicketCategoriesRepositoryJPA(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }

    @Override
    public void registerTicketCategory(TicketCategory ticketCategory) {
        EntityManager em = jpaApi.em();
        em.persist(ticketCategory);
    }

    @Override
    public TicketCategory getTicketCategoryById(Integer ticketCategoryId) {
        EntityManager em = jpaApi.em();
        return em.find(TicketCategory.class, ticketCategoryId);
    }

    @Override
    public void persistTicket(Ticket ticket) {
        EntityManager em = this.jpaApi.em();
        em.persist(ticket);
    }
}
