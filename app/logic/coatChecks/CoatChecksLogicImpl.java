package logic.coatChecks;

import dal.coatChecks.CoatChecksRepository;
import models.events.CoatHanger;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;
import org.joda.time.DateTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by marco on 23.04.2017.
 */
public class CoatChecksLogicImpl implements CoatChecksLogic {
    private final CoatChecksRepository coatChecksRepository;

    @Inject
    public CoatChecksLogicImpl(CoatChecksRepository coatChecksRepository){
        this.coatChecksRepository = coatChecksRepository;
    }

    @Override
    public CoatCheck createNewCoatCheck(User user, CoatHanger coatHanger) {
        return coatChecksRepository.createNewCoatCheck(user, coatHanger, new Date());
    }

    @Override
    public CoatHanger fetchJacket(Date fetchedOn, CoatCheck coatCheck) {
        return coatChecksRepository.fetchJacket(fetchedOn, coatCheck);
    }
}
