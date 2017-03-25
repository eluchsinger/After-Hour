package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Ticket Category
 */
public class TicketCategory {
    private ArrayList<SoldTicket> soldTickets;
    protected Date ticketStartDateTime;
    protected Date ticketStopDateTime;

    public void addSoldTicket(SoldTicket soldTicket){
        soldTickets.add(soldTicket);
    }

    public ArrayList<SoldTicket> getSoldTickets(){
        return soldTickets;
    }
}
