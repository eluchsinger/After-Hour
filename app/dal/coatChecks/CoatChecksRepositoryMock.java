package dal.coatChecks;

import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatChecksRepositoryMock implements CoatChecksRepository {
    private List<CoatCheck> coatChecks;
    private List<CoatHanger> coatHangers;

    public CoatChecksRepositoryMock(){
        this.coatChecks = new ArrayList<>();
        this.coatHangers = new ArrayList<>();
        Location plaza = new Location(1, "Plaza", "", "Plaza");
        coatHangers.add(new CoatHanger(1, 1, plaza));
        coatHangers.add(new CoatHanger(2, 2, plaza));
        coatHangers.add(new CoatHanger(2, 3, plaza));
    }

    @Override
    public void persistNewCoatHanger(CoatHanger coatHanger) {

    }

    @Override
    public CoatHanger getCoatHangerByNumberAndLocationID(Integer coatHangerNumber, String locationID) {
        return coatHangers
                .stream()
                .filter(c -> Objects.equals(c.getCoatHangerNumber(), coatHangerNumber) && Objects.equals(c.getLocation().getPlaceId(), locationID))
                .findFirst()
                .orElse(null);
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
        CoatHanger coatHanger = getCoatHangerByNumberAndLocationID(coatHangerNumber, location.getPlaceId());
        CoatCheck coatCheck = new CoatCheck(coatHanger, handOverOn, user);
        coatChecks.add(coatCheck);
        return coatCheck;
    }

    @Override
    public CoatHanger fetchJacket(Date fetchedOn, Integer coatCheckID) {
        return null;
    }
}
