package dal.coatChecks;

import demoData.DemoData;
import models.events.CoatHanger;
import models.tickets.CoatCheck;
import models.users.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public CoatCheck createNewCoatCheck(User user, CoatHanger coatHanger, Date handOverOn) {
        CoatCheck coatCheck = new CoatCheck(coatHanger, handOverOn, user);
        coatChecks.add(coatCheck);
        return coatCheck;
    }

    @Override
    public CoatHanger fetchJacket(Date fetchedOn, CoatCheck coatCheck) {
        CoatCheck dBCoatCheck = coatChecks
                                            .stream()
                                            .filter(c -> coatCheck.getCoatHanger() == null ? false : coatCheck.getCoatHanger().equals(c.getCoatHanger()))
                                            .findFirst()
                                            .orElse(null);


        return dBCoatCheck != null ? dBCoatCheck.fetch(fetchedOn) : null;
    }
}
