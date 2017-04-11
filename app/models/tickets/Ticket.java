package models.tickets;

import models.events.TicketCategory;
import models.users.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 * A ticket bought by a User.
 */
@Entity
@Table(name = "tbl_tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    private Date soldDate;
    @ManyToOne(targetEntity = TicketCategory.class)
    private TicketCategory ticketCategory;

    public Ticket() {

    }

    public Ticket(final User user, final Date soldDate, final TicketCategory ticketCategory){
        this.user = user;
        this.soldDate = soldDate;
        this.ticketCategory = ticketCategory;
    }
}
