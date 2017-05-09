package models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.tickets.CoatCheck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
@Entity
@NamedQuery(name="Event.getAll", query="SELECT e FROM Event e")
@Table(name = "tbl_events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @ManyToOne(targetEntity = Location.class)
    private Location location;
    @Transient
    private Organizer organizer;
    private Date eventDate;

    @OneToMany(mappedBy = "event")
    private List<TicketCategory> ticketCategories;
    @JsonIgnore
    @Transient
    private ArrayList<CoatCheck> coatChecks;


    //region Constructors

    public Event(){
        this(null, null, null);
    }

    public Event(final Integer id, final String title, final String description) {
        this(id, title, description, null, new Date());
    }
    public Event(final Integer id, final String title, final String description,
                 final Location location, final Date eventDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.ticketCategories = new ArrayList<>();
        this.coatChecks = new ArrayList<>();
    }

    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(final Integer value) {
        this.id = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String value) {
        this.title = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String value) {
        this.description = value;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(final Date eventDate) {
        this.eventDate = eventDate;
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
