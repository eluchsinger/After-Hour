package dataLayer.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dataLayer.tickets.SoldTicket;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Ticket Category
 */
public class TicketCategory {
    @JsonIgnore
    protected ArrayList<SoldTicket> soldTickets;
    @JsonIgnore
    protected Date ticketStartDateTime;
    @JsonIgnore
    protected Date ticketStopDateTime;

    public void addSoldTicket(SoldTicket soldTicket){
        soldTickets.add(soldTicket);
    }

    public ArrayList<SoldTicket> getSoldTickets(){
        return soldTickets;
    }

    public void setTicketStartDateTime(Date date){
        this.ticketStartDateTime = date;
    }

    public Date getTicketStartDateTime(){
        return ticketStartDateTime;
    }

    public void setTicketStopDateTime(Date date){
        this.ticketStopDateTime = date;
    }

    public Date getTicketStopDateTime(){
        return ticketStopDateTime;
    }


}
