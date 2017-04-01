package dataLayer.events;

import dataLayer.events.TicketCategory;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * An Entry Ticket for an costumer
 */
public class EntryTicket extends TicketCategory {
    private String description;
    private int price;

    public  EntryTicket(String description, int price, Date ticketStartDateTime, Date ticketStopDateTime){
        this.price = price;
        this.description = description;
        this.ticketStartDateTime = ticketStartDateTime;
        this.ticketStopDateTime = ticketStopDateTime;
        this.soldTickets = new ArrayList<>();
    }

    public EntryTicket(String description, int price){
        this.description = description;
        this.price = price;
    }

    public String getDescription(){
        return description;
    }
}
