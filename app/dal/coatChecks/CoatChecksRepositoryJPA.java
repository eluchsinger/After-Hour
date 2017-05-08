package dal.coatChecks;

import models.events.CoatHanger;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;
import play.db.jpa.JPAApi;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatChecksRepositoryJPA implements CoatChecksRepository {
    private final JPAApi jpaApi;

    @Inject
    public CoatChecksRepositoryJPA(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }

    @Override
    public CoatCheck createNewCoatCheck(User user, CoatHanger coatHanger, Date handOverOn) {
        final EntityManager em = jpaApi.em();
        CoatCheck coatCheck = new CoatCheck(coatHanger, handOverOn, user);
        em.persist(coatCheck);
        return coatCheck;
    }

    @Override
    public CoatHanger fetchJacket(Date fetchedOn, CoatCheck coatCheck) {
        final EntityManager em = jpaApi.em();
        CoatHanger hangerToFetchJacket = coatCheck.fetch(fetchedOn);
        em.refresh(coatCheck);
        return hangerToFetchJacket;
    }
}
