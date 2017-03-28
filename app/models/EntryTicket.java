package models;

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
    }

    public EntryTicket(String description, int price){
        this.description = description;
        this.price = price;
    }
}
