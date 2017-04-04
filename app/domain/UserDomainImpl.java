package domain;

import dal.UsersRepository;
import models.users.User;

import javax.inject.Inject;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UserDomainImpl implements UserDomain {
    private UsersRepository usersRepository;

    @Inject
    public UserDomainImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUserById(Integer userId) {
        return this.usersRepository.getUserById(userId);
    }
}
