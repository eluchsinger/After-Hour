package dal.events;

import models.events.Event;
import models.events.Location;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 * The EventsRepository handles the CRUD operations in regards of {@link Event}
 * and {@link Location} models.
 */
public interface EventsRepository {
    /**
     * Gets an Event by eventId.
     * @param eventId The unique ID of the event.
     * @return Returns the found event or null, if no event was found.
     */
    Event getEventById(Integer eventId);

    /**
     * Adds a new event to the events-repository.
     * @param event The object of type {@link Event} to add into the repository.
     */
    void registerEvent(Event event);

    /**
     * Removes the event corresponding to the eventId.
     * @param eventId The unique ID of the {@link Event}.
     * @return Returns true, if an event was removed, false if nothing was removed.
     */
    boolean removeEventById(Integer eventId);

    /**
     * Returns the {@link Location} by its Unique-Id (NOT the PlacesID!)
     * @param locationId The unique id of the location (NOT the same as the PlacesID).
     * @return Return the found location or null, if no location was found.
     */
    Location getLocationById(Integer locationId);

    /**
     * Adds a new location into the repository.
     * @param location The location to be added to the repository.
     */
    void addLocation(Location location);
}
