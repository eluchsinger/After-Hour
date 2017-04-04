package domain;

import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public interface UserDomain {
    User getUserById(Integer userId);
}
