package logic.users;

import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public interface UsersLogic {
    User getUserById(Integer userId);
    void registerUser(User user);
    User getUserByEmail(String email);
}
