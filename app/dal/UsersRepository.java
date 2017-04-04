package dal;

import models.tickets.SoldTicket;
import models.users.User;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 */
public interface UsersRepository {
    User getUserById(Integer userId);
    SoldTicket getEventTicket(Integer userId, Integer eventId);
}
