package dal.events;

import models.events.Event;
import models.events.Location;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

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
    public void registerEvent(Event event) {
        EntityManager em = jpaApi.em();
        em.persist(event);
    }

    @Override
    public Location getLocationById(final Integer locationId) {
        EntityManager em = this.jpaApi.em();
        return em.find(Location.class, locationId);
    }

    @Override
    public void addLocation(Location location) {
        EntityManager em = jpaApi.em();
        em.persist(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents() {
        EntityManager em = jpaApi.em();
        return em.createNamedQuery("Event.getAll", Event.class).getResultList();
    }
}
