package models.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.tickets.CoatCheck;

import java.util.ArrayList;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
<<<<<<< HEAD
=======
@Entity
@NamedQuery(name="Event.getAll", query="SELECT e FROM Event e")
@Table(name = "tbl_events", schema = "public")
>>>>>>> refs/remotes/origin/developer
public class Event {
    private int id;
    private String title;
    private String description;
<<<<<<< HEAD
=======
    @ManyToOne(targetEntity = Location.class)
>>>>>>> refs/remotes/origin/developer
    private Location location;
    private Organizer organizer;
<<<<<<< HEAD
    @JsonIgnore
    private ArrayList<TicketCategory> ticketCategories;
=======
    @OneToMany(mappedBy = "event")
    private List<TicketCategory> ticketCategories;
>>>>>>> refs/remotes/origin/developer
    @JsonIgnore
    private ArrayList<CoatCheck> coatChecks;

<<<<<<< HEAD
    public Event(final int id, final String title, final String description) {
=======

    //region Constructors

    public Event(){
        this(null, null, null);
    }

    public Event(final Integer id, final String title, final String description) {
        this(id, title, description, null);
    }
    public Event(final Integer id, final String title, final String description, final Location location) {
>>>>>>> refs/remotes/origin/developer
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.ticketCategories = new ArrayList<>();
        this.coatChecks = new ArrayList<>();
    }

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

    public ArrayList<TicketCategory> getTicketCategories(){
        return ticketCategories;
    }

    public void addCoatCheck(CoatCheck coatCheck){
        coatChecks.add(coatCheck);
    }

    public ArrayList<CoatCheck> getCoatChecks(){
        return coatChecks;
    }
}
