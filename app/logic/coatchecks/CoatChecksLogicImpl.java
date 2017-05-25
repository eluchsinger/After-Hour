package logic.coatchecks;

import dal.coatchecks.CoatChecksRepository;
import dal.events.EventsRepository;
import dal.users.UsersRepository;
import models.events.Location;
import models.tickets.CoatCheck;
import models.users.User;

import javax.inject.Inject;
import java.util.Date;

public class CoatChecksLogicImpl implements CoatChecksLogic {
    private final CoatChecksRepository coatChecksRepository;
    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;

    @Inject
    public CoatChecksLogicImpl(CoatChecksRepository coatChecksRepository, UsersRepository usersRepository, EventsRepository eventsRepository){
        this.coatChecksRepository = coatChecksRepository;
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
    }

    @Override
    public CoatCheck createNewCoatCheck(String email, Integer coatHangerNumber, String locationName) {
        User user = usersRepository.getUserByEmail(email);
        Location location = eventsRepository.getLocationByName(locationName);
        return coatChecksRepository.createNewCoatCheck(user, location, new Date(), coatHangerNumber);
    }

    @Override
    public Boolean fetchJacket(Date date, Integer coatHangerID) {
        return coatChecksRepository.fetchJacket(date, coatHangerID);
    }
}
