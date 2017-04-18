package logic.users;

import dal.users.UsersRepository;
import models.users.User;

import javax.inject.Inject;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersLogicImpl implements UsersLogic {
    private UsersRepository usersRepository;

    @Inject
    public UsersLogicImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User getUserById(Integer userId) {
        return this.usersRepository.getUserById(userId);
    }

    @Override
    public User registerUser(User user){return this.usersRepository.registerUser(user);}
}
