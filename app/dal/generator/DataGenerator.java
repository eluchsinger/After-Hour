package dal.generator;

import dal.events.EventsRepository;
import dal.users.UsersRepository;
import models.events.Event;
import models.events.TicketCategory;
import models.users.Gender;
import models.users.User;
import play.Logger;
import play.db.jpa.Transactional;

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
    private final static int INITIAL_TICKET_CATEGORY_CAPAZITY = 10;

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
    @Transactional
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
    @Transactional
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

    @Transactional
    private int generateTicketCategories(){
        //Todo: Implement Ticket Category Demo Data and store it in the Repo.

        return 0;
    }

    private List<User> getDemoUsers() throws ParseException {
        final List<User> users = new ArrayList<>(INITIAL_USERS_CAPACITY);
        users.add(new User(null, "silvio.berlusconi@italy.it", "Berlusconi","Silvio", this.dateFormat.parse("1950-09-11"), Gender.MALE));
        users.add(new User(null, "i.beller@cervelat.de", "Beller", "Irina", this.dateFormat.parse("1900-03-12"), Gender.FEMALE));
        users.add(new User(null, "franz.becki@idc.yolo", "Beckenbauer", "Franz Anton",this.dateFormat.parse("1945-09-11"), Gender.MALE));
        users.add(new User(null, "g.n@netz.los", "Netzer", "Günther", this.dateFormat.parse("1944-09-14"), Gender.MALE));

        return users;
    }

    private List<Event> getDemoEvents() {
        final List<Event> events = new ArrayList<>(INITIAL_EVENTS_CAPACITY);
        events.add(new Event(null, "Bobba Fett Party", "Sei wie Bobba. Sei Fett."));
        events.add(new Event(null, "Nachtseminar", "DIE Party für Studis"));
        events.add(new Event (null, "Duschi Abgstellt Party", "Party für Fussballer nach dem Duschen"));
        events.add(new Event(null,"Silvios Bunga Bunga Party", "Silvios exklusive Party für die 'gehobene' Gesellschaft)"));
        return events;
    }

    private List<TicketCategory> getTDemoTicketCategories(final EventsRepository eventsRepository) throws ParseException {
        final List<TicketCategory> ticketCategories = new ArrayList<>(INITIAL_TICKET_CATEGORY_CAPAZITY);
        ticketCategories.add(new TicketCategory(null, "Studenten Ticket", "Ticket für Studenten", eventsRepository.getEventById(1), 15.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20") ));
        return ticketCategories;
    }
}
