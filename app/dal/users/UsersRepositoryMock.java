package dal.users;

import demoData.DemoData;
import models.tickets.Ticket;
import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public class UsersRepositoryMock implements UsersRepository {

    public UsersRepositoryMock() {
    }

    @Override
    public User getUserById(Integer userId) {
        return DemoData.getInstance().getUsers()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User registerUser(User user) {
        return null;
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
