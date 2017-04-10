package dal.events;

import models.events.Event;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Fabian on 07.04.2017.
 */
public class EventsRepositoryJPA implements EventsRepository {
    public JPAApi jpaApi;

    @Inject
    public EventsRepositoryJPA(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }

    @Override
    public Event getEventById(Integer eventId) {
        EntityManager em = jpaApi.em();
        return em.find(Event.class, eventId);
    }

    @Override
    public Event registerEvent(Event event) {
        EntityManager em = jpaApi.em();
        em.persist(event);
        return event;
    }

    @Override
    public boolean removeEventById(Integer eventId) {
        return false;
    }
}
