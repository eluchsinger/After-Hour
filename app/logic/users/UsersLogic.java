package logic.users;

import models.events.Event;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistServerException;
import models.tickets.Ticket;
import models.users.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * Handles the user problem domain.
 */
public interface UsersLogic {
    /**
     * Gets a user by it's id.
     * @param userId The unique UserID of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    User getUserById(Integer userId) throws UserDoesNotExistServerException;

    /**
     * Registers a new user.
     * @param user The new user to register.
     */
    void registerUser(User user);

    /**
     * Returns a user by it's email.
     * @param email The email of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    User getUserByEmail(String email) throws UserDoesNotExistServerException;

    User login(String email, String password) throws ServerException;

    Ticket getTicket(Integer userId, Integer eventId) throws ServerException;

    List<Event> getEventsAvailable(Integer userId) throws UserDoesNotExistServerException;

    List<Event> getEventsAvailable(Integer userId, Date date) throws UserDoesNotExistServerException;
}
