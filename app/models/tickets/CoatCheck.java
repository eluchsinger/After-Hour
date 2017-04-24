package models.tickets;

import models.events.CoatHanger;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Coatcheck
 */
@Entity
@Table(name = "tbl_coatChecks")
public class CoatCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = CoatHanger.class)
    private CoatHanger coatHanger;
    private Date handOverOn;
    private Date fetchedOn;
    @ManyToOne(targetEntity = Ticket.class)
    private Ticket ticket;

    public CoatCheck(){
        this.id = null;
    }

    public CoatCheck(CoatHanger coatHanger, Date handOverOn){
        this.coatHanger = coatHanger;
        this.handOverOn = handOverOn;
        this.fetchedOn = null;
    }

    public boolean isValid(){
        return this.fetchedOn == null;
    }
    public void setFetchedOn(Date fetchedOn) {this.fetchedOn = fetchedOn;}
}
