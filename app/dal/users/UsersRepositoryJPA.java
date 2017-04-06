package dal.users;

import models.tickets.Ticket;
import models.users.User;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersRepositoryJPA implements UsersRepository {
    public JPAApi jpaApi;

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
    @Transactional
    public User registerUser(User user) {
        EntityManager em = jpaApi.em();
        em.persist(user);
        return user;
    }

    @Override
    @Transactional
    public boolean removeUserById(Integer userId) {
        EntityManager em = jpaApi.em();
        User userToRemove = em.find(User.class, userId);

        if(userToRemove != null) {
            em.remove(userToRemove);
            return true;
        }
        return false;
    }

    @Override
    public Ticket getEventTicket(Integer userId, Integer eventId) {
        return null;
    }
}
