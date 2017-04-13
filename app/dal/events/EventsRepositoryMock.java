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

    public EventsRepositoryMock(){
        this.events = DemoData.getInstance().getEvents();
        this.locations = new ArrayList<>();
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
        events.add(event);
    }

    @Override
    public boolean removeEventById(Integer eventId) {
        return events.removeIf(event -> event.getId().equals(eventId));
    }

    @Override
    public Location getLocationById(Integer locationId) {
        return this.locations.stream()
                .filter(location -> location.getId().equals(locationId))
                .findFirst().orElse(null);
    }

    @Override
    public void addLocation(Location location) {
        this.locations.add(location);
    }
}
