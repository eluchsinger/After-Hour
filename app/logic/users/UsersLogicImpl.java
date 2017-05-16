package logic.users;

import dal.users.UsersRepository;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistException;
import models.exceptions.UserHasNoTicketException;
import models.exceptions.UserWrongPasswordException;
import models.tickets.Ticket;
import models.users.User;

import javax.inject.Inject;
import java.util.Optional;

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
     * @return Returns the found user or null, if nothing was found.
     */
    @Override
    public User getUserById(Integer userId) {
        return this.usersRepository.getUserById(userId);
    }

    /**
     * Registers a new user.
     * @param user The new user to register.
     */
    @Override
    public void registerUser(User user){ this.usersRepository.registerUser(user); }

    /**
     * Returns a user by it's email.
     * @param email The email of the user to get.
     * @return Returns the found user or null, if nothing was found.
     */
    @Override
    public User getUserByEmail(String email){return this.usersRepository.getUserByEmail(email);}

    @Override
    public User login(String email, String password) throws ServerException {
        User user = usersRepository.getUserByEmail(email);

        if (!validateUser(user))
            throw new UserDoesNotExistException();

        if (!user.compareWithPassword(password))
            throw new UserWrongPasswordException();

        return user;
    }

    @Override
    public Ticket getTicket(Integer userId, Integer eventId) throws ServerException {
        User user = this.usersRepository.getUserById(userId);

        if (!validateUser(user))
            throw new UserDoesNotExistException();

        Optional<Ticket> ticket = user.getTickets().stream()
                .filter(x -> x.getTicketCategory().getEvent().getId() == eventId)
                .findFirst();

        if (!checkOptionalTicket(ticket))
            throw new UserHasNoTicketException();

        return ticket.get();
    }

    private boolean validateUser(final User user){
        return user != null;
    }

    private boolean checkOptionalTicket(final Optional<Ticket> ticket){
        return ticket.isPresent();
    }
}
