package models.events;

import models.tickets.Ticket;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fabian on 25.03.2017.
 * A Ticket Category
 */
@Entity
@Table(name = "tbl_ticketcategories")
public class TicketCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private Event event;
    @OneToMany(mappedBy = "ticketCategory")
    private List<Ticket> soldTickets;
    private double price;
    private Date startAvailability;
    private Date endAvailability;

    //region Constructors

    public TicketCategory(){
        this.id = null;
        this.soldTickets = new ArrayList<>();
    }

    public TicketCategory(final Integer id, final String name, final String description, final Event event, final Double price, final Date startAvailability, final Date endAvailability){
        this.id = id;
        this.name = name;
        this.description = description;
        this.event = event;
        this.price = price;
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.soldTickets = new ArrayList<>();
    }

    //endregion

    public void addSoldTicket(Ticket ticket){
        soldTickets.add(ticket);
    }

    public List<Ticket> getSoldTickets(){
        return soldTickets;
    }

    public Integer getId(){
        return id;
    }

}
