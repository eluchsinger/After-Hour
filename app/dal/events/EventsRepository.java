package dal.events;

import models.events.Event;
import models.events.Location;

/**
 * Created by Esteban Luchsinger on 06.04.2017.
 */
public interface EventsRepository {
    Event getEventById(Integer eventId);
    Event registerEvent(Event event);
    boolean removeEventById(Integer eventId);
    Location getLocationById(Integer locationId);
    void addLocation(Location location);
}
