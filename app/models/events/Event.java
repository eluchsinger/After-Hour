package models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.tickets.CoatCheck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
@Entity
@Table(name = "tbl_events", schema = "public")
public class Event {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    @Transient
    private Location location;
    @Transient
    private Organizer organizer;
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<TicketCategory> ticketCategories;
    @JsonIgnore
    @Transient
    private ArrayList<CoatCheck> coatChecks;


    //region Constructors

    public Event(){
        this.id = null;
        ticketCategories = new ArrayList<>();
    }

    public Event(final Integer id, final String title, final String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ticketCategories = new ArrayList<>();
        this.coatChecks = new ArrayList<>();
    }

    //endregion

    //region Getters and Setters

    public void setId(final int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public void setTitle(final String value) {
        this.title = value;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(final String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setOrganizer(Organizer organizer){
        this.organizer = organizer;
    }

    public void addTicketCategory(TicketCategory ticket){
        ticketCategories.add(ticket);
    }

    public List<TicketCategory> getTicketCategories(){
        return ticketCategories;
    }

    public void addCoatCheck(CoatCheck coatCheck){
       coatChecks.add(coatCheck);
    }

    public List<CoatCheck> getCoatChecks(){
        return coatChecks;
    }

    //endregion
}
