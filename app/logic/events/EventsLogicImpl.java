package logic.events;

import dal.events.EventsRepository;
import models.events.Event;

import javax.inject.Inject;

/**
 * Created by Fabian on 08.04.17.
 */
public class EventsLogicImpl implements EventsLogic {
    private EventsRepository eventsRepository;

    @Inject
    public EventsLogicImpl(EventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Event getEventById(Integer EventId) {
        return eventsRepository.getEventById(EventId);
    }
}
