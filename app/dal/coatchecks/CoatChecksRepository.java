package dal.coatchecks;

import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import java.util.Date;

public interface CoatChecksRepository {

    void persistNewCoatHanger(CoatHanger coatHanger);

    CoatHanger getCoatHangerByNumberAndLocationID(Integer coatHangerNumber, String locationID);

    void addNewCoatHanger(CoatHanger coatHanger);

    CoatCheck getCoatCheckByPublicIdentifier(Integer pid);

    void addNewCoatCheck(CoatCheck coatCheck);

    CoatCheck createNewCoatCheck(User user, Location location, Date handOverOn, Integer coatHangerNumber);

    Boolean fetchJacket(Date fetchedOn, Integer coatCheckID);
}
