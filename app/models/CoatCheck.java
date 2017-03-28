package models;

/**
 * Created by Fabian on 25.03.2017.
 * A Coatcheck
 */
public class CoatCheck extends TicketInstance {
    private int coatCheckNr;

    public CoatCheck(int coatCheckNr, User user, Event event){
        this.coatCheckNr = coatCheckNr;
        this.user = user;
        this.event = event;
    }
}
