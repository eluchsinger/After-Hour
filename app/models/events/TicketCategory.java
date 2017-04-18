package models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import models.tickets.SoldTicket;
=======
import models.tickets.Ticket;
import models.users.User;
>>>>>>> refs/remotes/origin/developer

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Ticket Category
 */
public class TicketCategory {
<<<<<<< HEAD
    protected ArrayList<SoldTicket> soldTickets;
    @JsonIgnore
    protected Date ticketStartDateTime;
    @JsonIgnore
    protected Date ticketStopDateTime;

    public void addSoldTicket(SoldTicket soldTicket){
        soldTickets.add(soldTicket);
=======
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

    public TicketCategory(){
        this.id = null;
        this.soldTickets = new ArrayList<>();
>>>>>>> refs/remotes/origin/developer
    }

    public ArrayList<SoldTicket> getSoldTickets(){
        return soldTickets;
    }

    public void setTicketStartDateTime(Date date){
        this.ticketStartDateTime = date;
    }

<<<<<<< HEAD
    public Date getTicketStartDateTime(){
        return ticketStartDateTime;
    }

    public void setTicketStopDateTime(Date date){
        this.ticketStopDateTime = date;
=======
    public Ticket sellTicket(final User user) {
        final Ticket soldTicket = this.ticketFactory.createTicket(user);
        this.soldTickets.add(soldTicket);
        return soldTicket;
    }

    @JsonIgnore
    public List<Ticket> getSoldTickets(){
        return soldTickets;
>>>>>>> refs/remotes/origin/developer
    }

    public Date getTicketStopDateTime(){
        return ticketStopDateTime;
    }

<<<<<<< HEAD
=======
    public String getDescription(){
        return description;
    }

    public Double getPrice() {
        return price;
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
>>>>>>> refs/remotes/origin/developer

}
