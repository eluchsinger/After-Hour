package dal.users;

import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * The EventsRepository handles the CRUD operations in regards of {@link User}
 */
public interface UsersRepository {
    /**
     * Finds and returns a {@link User} by its unique ID.
     * @param userId The unique ID of the searched {@link User}
     * @return Returns the found {@link User} or null if nothing found.
     */
    User getUserById(Integer userId);

    /**
     * Adds a new {@link User} to the Repository and persists it.
     * @param user The new user to persist into the Repository.
     */
    void registerUser(User user);

    /**
     * Removes a user by ID.
     * @param userId The ID of the user.
     * @return Returns true, if a user was removed. Else returns false.
     */
    boolean removeUserById(Integer userId);

    /**
     * Counts how many {@link User} are registered.
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
