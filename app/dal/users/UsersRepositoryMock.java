package dal.users;


import demoData.DemoData;
import models.tickets.Ticket;
import models.users.User;

import java.util.List;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersRepositoryMock implements UsersRepository {
    private List<User> users;

    public UsersRepositoryMock() {
        users = DemoData.getInstance().getUsers();
    }

    @Override
    public User getUserById(Integer userId) {
        return users
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User registerUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public boolean removeUserById(Integer userId) {
        return false;
    }

    @Override
    public Ticket getEventTicket(Integer userId, Integer eventId) {
        return null;
    }
}
