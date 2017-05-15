package models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.tickets.Ticket;
import models.users.User;

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

    @Transient
    private TicketFactory ticketFactory = new TicketFactory(this);

    //region Constructors

    public TicketCategory() {
        this.id = null;
        this.soldTickets = new ArrayList<>();
    }

    public TicketCategory(final Integer id, final String name, final String description, final Double price, final Date startAvailability, final Date endAvailability) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.soldTickets = new ArrayList<>();
    }

    public TicketCategory(final Integer id, final String name, final String description, final Event event, final Double price, final Date startAvailability, final Date endAvailability) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.event = event;
        this.price = price;
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.soldTickets = new ArrayList<>();

        if (event != null){
            event.addTicketCategory(this);
        }
    }

    //endregion

    //region Getters / Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Date getStartAvailability() {
        return startAvailability;
    }

    public Date getEndAvailability() {
        return endAvailability;
    }

    @JsonIgnore
    public Event getEvent(){
        return event;
    }

    @JsonIgnore
    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    //endregion Getters / Setters

    public Ticket sellTicket(final User user) {
        final Ticket soldTicket = this.ticketFactory.createTicket(user);
        this.soldTickets.add(soldTicket);
        user.addTicket(soldTicket);
        return soldTicket;
    }

    private class TicketFactory {
        private final TicketCategory ticketCategory;

        private TicketFactory(final TicketCategory category) {
            this.ticketCategory = category;
        }

        Ticket createTicket(final User user) {
            return new Ticket(user, new Date(), this.ticketCategory);
        }
    }
}
