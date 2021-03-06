package dal.coatchecks;

import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;

public class CoatChecksRepositoryJPA implements CoatChecksRepository {
    private final JPAApi jpaApi;

    @Inject
    public CoatChecksRepositoryJPA(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }

    @Override
    public void persistNewCoatHanger(CoatHanger coatHanger){
        final EntityManager em = jpaApi.em();
        em.persist(coatHanger);
    }

    @Override
    public CoatHanger getCoatHangerByNumberAndLocationID(Integer coatHangerNumber, String locationID) {
        final EntityManager em = jpaApi.em();
        TypedQuery<CoatHanger> q = em.createNamedQuery("CoatHanger.get", CoatHanger.class);
        q.setParameter("coatHangerNumber", coatHangerNumber);
        q.setParameter("locationPlaceID", locationID);
        return q.getSingleResult();
    }

    @Override
    public void addNewCoatHanger(CoatHanger coatHanger) {
        final EntityManager em = jpaApi.em();
        em.persist(coatHanger);
    }

    @Override
    public CoatCheck getCoatCheckByPublicIdentifier(Integer publicIdentifier) {
        final EntityManager em = jpaApi.em();
        TypedQuery<CoatCheck> q = em.createNamedQuery("CoatCheck.getByPublicIdentifier", CoatCheck.class);
        q.setParameter("publicIdentifier", publicIdentifier);
        return q.getSingleResult();
    }

    @Override
    public void addNewCoatCheck(CoatCheck coatCheck) {
        final EntityManager em = jpaApi.em();
        em.persist(coatCheck);
    }

    @Override
    public CoatCheck createNewCoatCheck(User user, Location location, Date handOverOn, Integer coatHangerNumber) {
        final EntityManager em = jpaApi.em();
        CoatHanger coatHanger = getCoatHangerByNumberAndLocationID(coatHangerNumber, location.getPlaceId());
        CoatCheck coatCheck = new CoatCheck(coatHanger, handOverOn, user);
        em.persist(coatCheck);
        return coatCheck;
    }

    @Override
    public Boolean fetchJacket(Date fetchedOn, Integer coatCheckPublicIdentifier) {
        final EntityManager em = jpaApi.em();
        CoatCheck coatCheck = getCoatCheckByPublicIdentifier(coatCheckPublicIdentifier);
        CoatHanger hangerToFetchJacket = coatCheck.fetch(fetchedOn);
        if(hangerToFetchJacket != null) {
            coatCheck.setCoatHanger(null);
            return true;
        }
        return false;
    }
}
