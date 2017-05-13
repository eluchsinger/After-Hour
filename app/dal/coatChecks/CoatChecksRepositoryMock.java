package dal.coatChecks;

import demoData.DemoData;
import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import java.util.Date;
import java.util.List;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatChecksRepositoryMock implements CoatChecksRepository {
    private List<CoatCheck> coatChecks;

    public CoatChecksRepositoryMock(){
        this.coatChecks = DemoData.getInstance().getCoatChecks();
    }

    @Override
    public void persistNewCoatHanger(CoatHanger coatHanger) {

    }

    @Override
    public CoatHanger getCoatHangerByNumberAndLocationID(Integer coatHangerNumber, String locationID) {
        return null;
    }

    @Override
    public CoatHanger getCoatHangerByID(Integer id) {
        return null;
    }

    @Override
    public CoatHanger addNewCoatHanger(CoatHanger coatHanger) {
        return null;
    }

    @Override
    public CoatCheck getCoatCheckByID(Integer id) {
        return null;
    }

    @Override
    public CoatCheck getCoatCheckByPublicIdentifier(Integer pid) {
        return null;
    }

    @Override
    public CoatCheck addNewCoatCheck(CoatCheck coatCheck) {
        return null;
    }

    @Override
    public CoatCheck createNewCoatCheck(User user, Location location, Date handOverOn, Integer coatHangerNumber) {
        return null;
    }

    @Override
    public CoatHanger fetchJacket(Date fetchedOn, Integer coatCheckID) {
        return null;
    }
}
