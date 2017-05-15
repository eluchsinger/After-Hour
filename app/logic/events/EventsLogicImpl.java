package logic.events;

import dal.events.EventsRepository;
import models.events.Event;
import models.events.TicketCategory;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Event getEventWithTicketCategories(Integer eventId, Boolean onlyAvailable) {
        return getEventWithTicketCategories(eventId, onlyAvailable, new Date());
    }

    public Event getEventWithTicketCategories(Integer eventId, Boolean onlyAvailable, Date date){
        Event event = eventsRepository.getEventById(eventId);

        if (onlyAvailable){
            List<TicketCategory> tickets= event.getTicketCategories().stream()
                    .filter(x -> x.getStartAvailability().before(date)
                    && x.getEndAvailability().after(date))
                    .collect(Collectors.toList());
            event.setTicketCategories(tickets);
        }
        return event;
    }
}
