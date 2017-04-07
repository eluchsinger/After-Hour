package models.events;

import models.tickets.Ticket;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Ticket Category
 */
@Entity
@Table(name = "tbl_ticketcategories")
public class TicketCategory {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    private Event event;
    //private List<Ticket> soldTickets Todo: Implement;
    private double price;
    private Date startAvailability;
    private Date endAvailability;

    public TicketCategory(){
        this.id = null;
    }


}
