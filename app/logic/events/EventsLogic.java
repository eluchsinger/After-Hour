package logic.events;

import models.events.Event;
import models.exceptions.ServerException;

import java.util.Date;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * Handles the Events problem domain.
 */
public interface EventsLogic {
    /**
     * Gets an {@link Event} object by it's unique id.
     * @param EventId The unique id of the event.
     * @return Returns an {@link Event} object, if one was found.
     * Else returns null.
     */
    Event getEventById(Integer EventId);

    /**
     * Gets a list of all available events.
     * (No filter)
     * @return Returns a list of all events.
     * The list can be empty but not null.
     */
    List<Event> getEvents();

    Event getEventWithTicketCategories(Integer eventId, Boolean available) throws ServerException;

    Event getEventWithTicketCategories(Integer eventId, Boolean available, Date date) throws ServerException;
}
