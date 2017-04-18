package dal.users;

import models.tickets.Ticket;
import models.users.User;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

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
    public User getUserByEmail(String email) {
        EntityManager em = jpaApi.em();
        TypedQuery<User> q = em.createNamedQuery("User.getUserByEmail", User.class);
        q.setParameter("email", email);
        return q.getSingleResult();
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

    /**
     * Counts how many users are registered
     *
     * @return Returns the amount of registered users.
     */
    @Override
    public long countUsers() {
        EntityManager em = jpaApi.em();

        return ((BigInteger)em.createNamedQuery("User.count").getSingleResult()).longValue();
    }

    /**
     * Resets the complete repository.
     * (Deletes all data)
     *
     * @return True, if resetted successfully.
     */
    @Override
    @Transactional
    public void resetRepository() {
        Logger.warn("Resetting the Users Repository.");
        EntityManager em = jpaApi.em();
        em.createNamedQuery("User.reset").executeUpdate();
    }

}
