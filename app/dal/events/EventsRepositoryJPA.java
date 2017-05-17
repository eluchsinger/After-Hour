package dal.events;

import models.events.Event;
import models.events.Location;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Fabian Schwyter on 07.04.2017.
 */
public class EventsRepositoryJPA implements EventsRepository {
    private final JPAApi jpaApi;

    @Inject
    public EventsRepositoryJPA(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }
    @Override
    public Event getEventById(Integer eventId) {
        final EntityManager em = jpaApi.em();
        return em.find(Event.class, eventId);
    }

    @Override
    public void registerEvent(Event event) {
        final EntityManager em = jpaApi.em();
        em.persist(event);
    }

    @Override
    public Location getLocationById(final Integer locationId) {
        final EntityManager em = this.jpaApi.em();
        return em.find(Location.class, locationId);
    }

    @Override
    public Location getLocationByName(String name){
        EntityManager em = jpaApi.em();
        TypedQuery<Location> q = em.createNamedQuery("Location.getLocationByName", Location.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    @Override
    public void addLocation(Location location) {
        final EntityManager em = jpaApi.em();
        em.persist(location);
    }

    @Override
    public List<Event> getEvents() {
        final EntityManager em = jpaApi.em();
        return em.createNamedQuery("Event.getAll", Event.class).getResultList();
    }
}
