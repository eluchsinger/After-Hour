package logic.coatChecks;

import models.events.CoatHanger;
import models.tickets.CoatCheck;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public interface CoatChecksLogic {

    CoatCheck createNewCoatCheck(String email, Integer coatHangerNumber, String locationName);

    CoatHanger fetchJacket(Date date, Integer coatHangerID);
}
