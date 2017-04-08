package dal.users;

import models.tickets.Ticket;
import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public interface UsersRepository {
    User getUserById(Integer userId);
    User registerUser(User user);

    /**
     * Removes a user by ID.
     * @param userId The ID of the user.
     * @return Returns true, if a user was removed. Else returns false.
     */
    boolean removeUserById(Integer userId);
    Ticket getEventTicket(Integer userId, Integer eventId);

    /**
     * Counts how many users are registered
     * @return Returns the amount of registered users.
     */
    long countUsers();

    /**
     * Resets the complete repository.
     * (Deletes all user data)
     * WARNING: When the users are deleted, their corresponding tickets will also be deleted.
     */
    void resetRepository();
}
