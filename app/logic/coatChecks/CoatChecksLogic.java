package logic.coatChecks;

import models.events.CoatHanger;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public interface CoatChecksLogic {

    CoatCheck createNewCoatCheck(User user, CoatHanger coatHanger);

    CoatHanger fetchJacket(Date fetchedOn, CoatCheck coatCheck);
}
