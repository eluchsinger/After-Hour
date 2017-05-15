package dal.events;

import models.events.Event;
import models.events.Location;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian Schwyter on 08.04.17.
 */
@Singleton
public class EventsRepositoryMock implements EventsRepository {
    private List<Event> events;
    private List<Location> locations;
    private int eventIdCounter;
    private int locationIdCounter;

    public EventsRepositoryMock(){
        this.events = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.eventIdCounter = 1;
        this.locationIdCounter = 1;
    }

    @Override
    public Event getEventById(Integer eventId) {
        return events
                .stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void registerEvent(Event event) {
        event.setId(eventIdCounter++);
        events.add(event);
    }

    @Override
    public Location getLocationById(Integer locationId) {
        return this.locations.stream()
                .filter(location -> location.getId().equals(locationId))
                .findFirst().orElse(null);
    }

    @Override
    public Location getLocationByName(String name) {
        return this.locations.stream()
                .filter(location -> location.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public void addLocation(Location location) {
        location.setId(locationIdCounter++);
        this.locations.add(location);
    }

    @Override
    public List<Event> getEvents() {
        return this.events;
    }
}
