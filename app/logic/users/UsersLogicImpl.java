package logic.users;

import dal.users.UsersRepository;
import models.events.Event;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistServerException;
import models.exceptions.UserHasNoTicketServerException;
import models.exceptions.UserWrongPasswordServerException;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.User;
import models.utils.TimeIgnoringDateComparator;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Esteban Luchsinger on 04.04.2017.
 * Handles the user problem domain.
 */
public class UsersLogicImpl implements UsersLogic {
    private UsersRepository usersRepository;

    @Inject
    public UsersLogicImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Gets a user by it's id.
     * @param userId The unique UserID of the user to get.
     * @return Returns the found user or an UserDoesNotExistServerException, if nothing was found.
     */
    @Override
    public User getUserById(Integer userId) throws UserDoesNotExistServerException {
        User user = this.usersRepository.getUserById(userId);

        if (!validateUser(user))
            throw new UserDoesNotExistServerException();

        return user;
    }

    /**
     * Registers a new user.
     * @param user The new user to register.
     */
    @Override
    public void registerUser(User user){
        this.usersRepository.registerUser(user);
    }

    /**
     * Returns a user by it's email.
     * @param email The email of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    @Override
    public User getUserByEmail(String email) throws UserDoesNotExistServerException {
        User user = this.usersRepository.getUserByEmail(email);

        if (!validateUser(user))
            throw new UserDoesNotExistServerException();

        return user;

    }

    @Override
    public User login(String email, String password) throws ServerException {
        User user = usersRepository.getUserByEmail(email);

        List<CoatCheck> validCoatChecks = user.getCoatChecks()
                .stream()
                .filter(c -> c.getFetchedOn() == null)
                .collect(Collectors.toList());

        user.setCoatChecks(validCoatChecks);

        if (!validateUser(user))
            throw new UserDoesNotExistServerException();

        if (!user.compareWithPassword(password))
            throw new UserWrongPasswordServerException();

        return user;
    }

    @Override
    public Ticket getTicket(Integer userId, Integer eventId) throws ServerException {
        User user = this.usersRepository.getUserById(userId);

        if (!validateUser(user))
            throw new UserDoesNotExistServerException();

        Optional<Ticket> ticket = user.getTickets().stream()
                .filter(x -> x.getTicketCategory().getEvent().getId() == eventId)
                .findFirst();

        if (!ticket.isPresent())
            throw new UserHasNoTicketServerException();

        return ticket.get();
    }

    @Override
    public List<Event> getEventsAvailable(final Integer userId) throws UserDoesNotExistServerException {
        return getEventsAvailable(userId, new Date());
    }

    @Override
    public List<Event> getEventsAvailable(Integer userId, Date date) throws UserDoesNotExistServerException {
        User user = this.usersRepository.getUserById(userId);

        if (!validateUser(user))
            throw new UserDoesNotExistServerException();

        return user.getTickets()
                .stream()
                .map(x -> x.getTicketCategory().getEvent())
                .filter(event -> new TimeIgnoringDateComparator().compare(event.getEventDate(),date) > 0)
                .collect(Collectors.toList());
    }


    private boolean validateUser(final User user){
        return user != null;
    }
}
