package models;

import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * Sold Ticket.
 */
public class SoldTicket extends TicketInstance {
    private Date soldDate;
    private TicketCategory ticketCategory;

    public SoldTicket(User user, Event event, TicketCategory ticketCategory){
        this.soldDate = new Date();
        this.user = user;
        this.event = event;
        this.ticketCategory = ticketCategory;
    }

    public User getUser(){
        return user;
    }

    public Event getEvent(){
        return event;
    }

    public TicketCategory getTicketCategory(){
        return ticketCategory;
    }
}
