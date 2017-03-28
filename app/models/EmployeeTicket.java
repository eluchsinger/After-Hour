package models;

import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * An EmployeeTicket for eomployees.
 */
public class EmployeeTicket extends TicketCategory {
    public EmployeeTicket(){

    }
    public EmployeeTicket(Date ticketStartDateTime, Date ticketStopDateTime){
        this.ticketStartDateTime = ticketStartDateTime;
        this.ticketStopDateTime = ticketStopDateTime;
    }
}
