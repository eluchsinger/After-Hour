package dal.coatChecks;

import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public interface CoatChecksRepository {

    void persistNewCoatHanger(CoatHanger coatHanger);

    CoatHanger getCoatHangerByNumberAndLocationID(Integer coatHangerNumber, String locationID);

    CoatHanger getCoatHangerByID(Integer id);

    CoatHanger addNewCoatHanger(CoatHanger coatHanger);

    CoatCheck getCoatCheckByID(Integer id);

    CoatCheck getCoatCheckByPublicIdentifier(Integer pid);

    CoatCheck addNewCoatCheck(CoatCheck coatCheck);

    CoatCheck createNewCoatCheck(User user, Location location, Date handOverOn, Integer coatHangerNumber);

    CoatHanger fetchJacket(Date fetchedOn, Integer coatCheckID);
}
