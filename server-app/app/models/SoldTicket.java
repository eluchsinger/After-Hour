package models;

import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * Sold Ticket.
 */
public class SoldTicket extends TicketInstance {
    private Date soldDate;

    public SoldTicket(){
        this.soldDate = new Date();
    }
}
