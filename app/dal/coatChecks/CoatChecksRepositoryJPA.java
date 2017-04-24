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
    public CoatCheck getCoatCheck(Ticket ticket) {
        final EntityManager em = jpaApi.em();
        throw new NotImplementedException();
    }

    @Override
    public CoatCheck getAllCoatChecksOfUser(User user) {
        final EntityManager em = jpaApi.em();
    }

    @Override
    public CoatCheck createNewCoatCheck(Ticket ticket, CoatHanger coatHanger, Date handOverOn) {
        final EntityManager em = jpaApi.em();
        CoatCheck coatCheck = new CoatCheck();
    }

    @Override
    public void fetchJacket(Date fetchedOn, CoatCheck coatCheck) {
        final EntityManager em = jpaApi.em();
        coatCheck.setFetchedOn(fetchedOn);
        em.refresh(coatCheck);
    }
}
