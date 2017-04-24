package logic.coatChecks;

import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatChecksLogicImpl implements CoatChecksLogic {
    @Override
    public CoatCheck getCoatCheck(Ticket ticket, User user) {
        return null;
    }

    @Override
    public CoatCheck getAllCoatChecksOfUser(User user) {
        return null;
    }

    @Override
    public CoatCheck createNewCoatCheck(Ticket ticket, User user) {
        return null;
    }

    @Override
    public CoatCheck fetchJacket(Date fetchedOn, CoatCheck coatCheck) {
        return null;
    }
}
