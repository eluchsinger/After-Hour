package dal.jpa;

import com.fasterxml.jackson.databind.JsonNode;
import dal.UsersRepository;
import models.tickets.SoldTicket;
import models.users.User;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersRepositoryJPA implements UsersRepository {
    private JPAApi jpaApi;

    @Inject
    public UsersRepositoryJPA(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Integer userId) {
        EntityManager em = jpaApi.em();
        return em.find(User.class, userId);
    }

    @Override
    public SoldTicket getEventTicket(Integer userId, Integer eventId) {
        return null;
    }
}
