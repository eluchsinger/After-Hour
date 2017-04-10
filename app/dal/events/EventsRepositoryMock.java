package dal.events;

import demoData.DemoData;
import models.events.Event;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Fabian on 08.04.17.
 */
public class EventsRepositoryMock implements EventsRepository {
    private List<Event> events;

    public EventsRepositoryMock(){
        this.events = DemoData.getInstance().getEvents();
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
    public Event registerEvent(Event event) {
        events.add(event);
        return event;
    }

    @Override
    public boolean removeEventById(Integer eventId) {
        return events.removeIf(event -> event.getId().equals(eventId));
    }
}
