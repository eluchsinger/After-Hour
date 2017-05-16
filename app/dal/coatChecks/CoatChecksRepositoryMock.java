package dal.coatChecks;

import models.events.CoatHanger;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by marco on 23.04.2017.
 */
@Singleton
public class CoatChecksRepositoryMock implements CoatChecksRepository {
    private List<CoatCheck> coatChecks;
    private List<CoatHanger> coatHangers;

    public CoatChecksRepositoryMock(){
        this.coatChecks = new ArrayList<>();
        this.coatHangers = new ArrayList<>();
    }

    @Override
    public void persistNewCoatHanger(CoatHanger coatHanger) {
        coatHangers.add(coatHanger);
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
        return coatHangers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public CoatHanger addNewCoatHanger(CoatHanger coatHanger) {
        coatHangers.add(coatHanger);
        return  coatHanger;
    }

    @Override
    public CoatCheck getCoatCheckByID(Integer id) {
        return coatChecks
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public CoatCheck getCoatCheckByPublicIdentifier(Integer pid) {
        return coatChecks
                .stream()
                .filter(c -> c.getPublicIdentifier().equals(pid))
                .findFirst()
                .orElse(null);
    }

    @Override
    public CoatCheck addNewCoatCheck(CoatCheck coatCheck) {
        coatChecks.add(coatCheck);
        return coatCheck;
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
        CoatHanger hanger = null;

        for(int i = 0; i < coatChecks.size(); i++){
            CoatCheck c = coatChecks.get(i);
            if(c.getPublicIdentifier().equals(coatCheckID)){
                hanger = c.fetch(fetchedOn);
                c.setCoatHanger(null);
                coatChecks.set(i, c);
                break;
            }
        }

        return hanger;
    }
}
