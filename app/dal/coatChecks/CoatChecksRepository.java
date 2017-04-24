package dal.coatChecks;

import models.events.CoatHanger;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public interface CoatChecksRepository {
    CoatCheck getCoatCheck(Ticket ticket);

    CoatCheck getAllCoatChecksOfUser(User user);

    CoatCheck createNewCoatCheck(Ticket ticket, CoatHanger coatHanger, Date handOverOn);

    void fetchJacket(Date fetchedOn, CoatCheck coatCheck);
}
