package dal.users;

import models.users.User;
import play.Logger;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * The EventsRepository handles the CRUD operations in regards of {@link User}
 * Uses a database to persist the changes.
 */
public class UsersRepositoryJPA implements UsersRepository {
    private final JPAApi jpaApi;

    @Inject
    public UsersRepositoryJPA(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    public User getUserById(Integer userId) {
        EntityManager em = jpaApi.em();
        return em.find(User.class, userId);
    }

    @Override
    public User getUserByEmail(String email) {
        EntityManager em = jpaApi.em();
        TypedQuery<User> q = em.createNamedQuery("User.getUserByEmail", User.class);
        q.setParameter("email", email);
        return q.getSingleResult();
    }

    @Override
    public void registerUser(User user) {
        EntityManager em = jpaApi.em();
        em.persist(user);
    }

    @Override
    public boolean removeUserById(Integer userId) {
        EntityManager em = jpaApi.em();
        User userToRemove = em.find(User.class, userId);

        if(userToRemove != null) {
            em.remove(userToRemove);
            return true;
        }
        return false;
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
    public void resetRepository() {
        Logger.warn("Resetting the Users Repository.");
        EntityManager em = jpaApi.em();
        em.createNamedQuery("User.reset").executeUpdate();
    }

}
