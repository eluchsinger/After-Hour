package dataLayer.tickets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dataLayer.events.Event;
import dataLayer.events.TicketCategory;
import dataLayer.users.User;

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

    @JsonIgnore
    public TicketCategory getTicketCategory(){
        return ticketCategory;
    }
}
