package logic.coatChecks;

import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public interface CoatChecksLogic {
    CoatCheck getCoatCheck(Ticket ticket, User user);

    CoatCheck getAllCoatChecksOfUser(User user);

    CoatCheck createNewCoatCheck(Ticket ticket, User user);

    CoatCheck fetchJacket(Date fetchedOn, CoatCheck coatCheck);
}
