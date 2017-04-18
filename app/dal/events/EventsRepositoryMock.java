package dal.events;

import demoData.DemoData;
import models.events.Event;
import models.events.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 08.04.17.
 */
public class EventsRepositoryMock implements EventsRepository {
    private List<Event> events;
    private List<Location> locations;
    private int eventId;
    private int locationId;

    public EventsRepositoryMock(){
        this.events = DemoData.getInstance().getEvents();
        this.locations = new ArrayList<>();
        this.eventId = 1;
        this.locationId = 1;
    }

    @Override
    public Event getEventById(Integer eventId) {
        return events
                .stream()
                .filter(event -> event.getId() == eventId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void registerEvent(Event event) {
        event.setId(eventId++);
        events.add(event);
    }

    @Override
    public Location getLocationById(Integer locationId) {
        return this.locations.stream()
                .filter(location -> location.getId().equals(locationId))
                .findFirst().orElse(null);
    }

    @Override
    public void addLocation(Location location) {
        location.setId(locationId++);
        this.locations.add(location);
    }
}
