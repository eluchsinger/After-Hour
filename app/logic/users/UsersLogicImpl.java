package logic.users;

import dal.users.UsersRepository;
import models.users.User;

import javax.inject.Inject;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * Handles the user problem domain.
 */
public class UsersLogicImpl implements UsersLogic {
    private UsersRepository usersRepository;

    @Inject
    public UsersLogicImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Gets a user by it's id.
     * @param userId The unique UserID of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    @Override
    public User getUserById(Integer userId) {
        return this.usersRepository.getUserById(userId);
    }

    /**
     * Registers a new user.
     * @param user The new user to register.
     */
    @Override
    public void registerUser(User user){ this.usersRepository.registerUser(user); }

    /**
     * Returns a user by it's email.
     * @param email The email of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    @Override
    public User getUserByEmail(String email){return this.usersRepository.getUserByEmail(email);}
}
