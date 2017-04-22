package logic.events;

import dal.events.EventsRepository;
import models.events.Event;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Fabian on 08.04.17.
 * Handles the Events problem domain.
 */
public class EventsLogicImpl implements EventsLogic {
    private final EventsRepository eventsRepository;

    @Inject
    public EventsLogicImpl(EventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }


    /**
     * Gets an {@link Event} object by it's unique id.
     *
     * @param EventId The unique id of the event.
     * @return Returns an {@link Event} object, if one was found.
     * Else returns null.
     */
    @Override
    public Event getEventById(Integer EventId) {
        return eventsRepository.getEventById(EventId);
    }

    /**
     * Gets a list of all available events.
     * (No filter)
     *
     * @return Returns a list of all events.
     * The list can be empty but not null.
     */
    @Override
    public List<Event> getEvents() {
        return eventsRepository.getEvents();
    }
}
