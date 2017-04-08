package dal.generator;

import dal.events.EventsRepository;
import dal.users.UsersRepository;
import models.events.Event;
import models.users.Gender;
import models.users.User;
import play.Logger;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 08.04.2017.
 * This class is used to generate demo-data.
 */
public class DataGenerator {
    private final static int INITIAL_USERS_CAPACITY = 10;
    private final static int INITIAL_EVENTS_CAPACITY = 10;

    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    @Inject
    public DataGenerator(final UsersRepository usersRepository, final EventsRepository eventsRepository) {
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
    }

    /**
     *  This method initializes the data for the repositories.
     */
    public void initializeData() throws GenerateException {
        confirmRepositoryNotNull(this.usersRepository, this.eventsRepository);

        final int amountOfUsers = this.generateUsers(this.usersRepository);
        final int amountOfEvents = this.generateEvents(this.eventsRepository);

        Logger.info("Generated " + amountOfUsers + " users");
        Logger.info("Generated " + amountOfEvents + " events");
    }

    /**
     * Checks if repositories are null.
     * @param repositories Repositories that can't be null
     * @param <T>
     * @throws GenerateException
     */
    @SafeVarargs
    private final <T> void confirmRepositoryNotNull(T... repositories) throws GenerateException {
        for(T repository : repositories) {
            if(repository == null) {
                throw new GenerateException("There can't be any null repositories when generating the data.",
                        new NullPointerException("At least one repository was null"));
            }
        }
    }

    /**
     *  Generate demo users.
     * @param usersRepository The repository in which the users will be generated.
     * @return Returns the amount of users generated.
     * @throws GenerateException Exception thrown, if the generation failed.
     */
    private int generateUsers(final UsersRepository usersRepository) throws GenerateException {
        try {
            final List<User> users = getDemoUsers();
            for(User user : users) {
                usersRepository.registerUser(user);
            }
            return users.size();
        }
        catch(ParseException parseException) {
            throw new GenerateException("Failed generating user data", parseException);
        }
    }

    /**
     *  Generate demo events
     * @param eventsRepository The repository in which the events will be generated.
     * @return Returns the amount of event generated.
     * @throws GenerateException Exception thrown, if the generation failed.
     */
    private int generateEvents(final EventsRepository eventsRepository) throws GenerateException {
        try {
            List<Event> events = getDemoEvents();
            for (Event event : events) {
                eventsRepository.registerEvent(event);
            }
            return events.size();
        }
        catch(Exception exception) {
            throw new GenerateException("Failed to generate events", exception);
        }
    }

    private List<User> getDemoUsers() throws ParseException {
        final List<User> users = new ArrayList<>(INITIAL_USERS_CAPACITY);
        users.add(new User(1, "silvio.berlusconi@italy.it", "Berlusconi","Silvio", this.dateFormat.parse("1950-09-11"), Gender.MALE));
        users.add(new User(2, "i.beller@cervelat.de", "Beller", "Irina", this.dateFormat.parse("1900-03-12"), Gender.FEMALE));
        users.add(new User(3, "franz.becki@idc.yolo", "Beckenbauer", "Franz Anton",this.dateFormat.parse("1945-09-11"), Gender.MALE));
        users.add(new User(4, "g.n@netz.los", "Netzer", "Günther", this.dateFormat.parse("1944-09-14"), Gender.MALE));

        return users;
    }

    private List<Event> getDemoEvents() {
        final List<Event> events = new ArrayList<>(INITIAL_EVENTS_CAPACITY);
        events.add(new Event(1, "Bobba Fett Party", "Sei wie Bobba. Sei Fett."));
        events.add(new Event(2, "Nachtseminar", "DIE Party für Studis"));
        events.add(new Event (3, "Duschi Abgstellt Party", "Party für Fussballer nach dem Duschen"));
        events.add(new Event(4,"Silvios Bunga Bunga Party", "Silvios exklusive Party für die 'gehobene' Gesellschaft)"));
        return events;
    }
}
