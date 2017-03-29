package dataLayer.events;

import dataLayer.tickets.CoatCheck;

import java.util.ArrayList;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private Location location;
    private Organizer organizer;
    private ArrayList<TicketCategory> ticketCategories;
    private ArrayList<CoatCheck> coatChecks;

    public Event(final int id, final String title, final String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
