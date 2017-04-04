package models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.events.Event;
import models.users.User;

/**
 * Created by Fabian on 25.03.2017.
 * TicketInstance
 */
public class TicketInstance {
    @JsonIgnore
    protected User user;
    protected Event event;
}
