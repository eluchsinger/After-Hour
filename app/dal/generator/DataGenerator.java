package dal.generator;

import dal.events.EventsRepository;
import dal.tickets.TicketRepository;
import dal.users.UsersRepository;
import models.events.Event;
import models.events.Location;
import models.events.TicketCategory;
import models.tickets.Ticket;
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
    private static final int INITIAL_USERS_CAPACITY = 4;
    private static final int INITIAL_EVENTS_CAPACITY = 5;
    private static final int INITIAL_TICKET_CATEGORY_CAPACITY = 10;
    private static final int INITIAL_TICKET_CAPACITY = 20;
    private static final int INITIAL_LOCATION_CAPACITY = 3;

    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;
    private final TicketRepository ticketRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * Initializes the DataGenerator using the respositories.
     *
     * @param usersRepository            The repository handling the users.
     * @param eventsRepository           The repository handling the events.
     * @param ticketRepository The repository handling the tickets.
     */
    @Inject
    public DataGenerator(final UsersRepository usersRepository,
                         final EventsRepository eventsRepository,
                         final TicketRepository ticketRepository) {
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * This method initializes the data for the repositories.
     */
    public void initializeData() throws GenerateException {
        confirmRepositoryNotNull(this.usersRepository,
                this.eventsRepository,
                this.ticketRepository);

        final int amountOfLocations = generateLocations(this.eventsRepository);
        final int amountOfUsers = generateUsers(this.usersRepository);
        final int amountOfEvents = generateEvents(this.eventsRepository);
        final int amountOfTicketCategories = generateTicketCategories(this.ticketRepository);
        final int amountOfTickets = generateTickets(this.usersRepository,
                this.ticketRepository);

        Logger.info("Generated " + amountOfUsers + " users");
        Logger.info("Generated " + amountOfEvents + " events");
        Logger.info("Generated " + amountOfTicketCategories + " ticketCategories");
        Logger.info("Generated " + amountOfTickets + " tickets");
        Logger.info("Generated " + amountOfLocations + " locations");
    }

    /**
     * Checks if repositories are null.
     *
     * @param repositories Repositories that can't be null
     * @param <T>
     * @throws GenerateException
     */
    @SafeVarargs
    private final <T> void confirmRepositoryNotNull(T... repositories) throws GenerateException {
        for (T repository : repositories) {
            if (repository == null) {
                throw new GenerateException("There can't be any null repositories " +
                        "when generating the data.",
                        new NullPointerException("At least one repository was null"));
            }
        }
    }

    /**
     * Generate demo users.
     *
     * @param usersRepository The repository in which the users will be generated.
     * @return Returns the amount of users generated.
     * @throws GenerateException Exception thrown, if the generation failed.
     */
    @Transactional
    private int generateUsers(final UsersRepository usersRepository) throws GenerateException {
        try {
            final List<User> users = getDemoUsers();
            for (User user : users) {
                usersRepository.registerUser(user);
            }
            return users.size();
        } catch (ParseException parseException) {
            throw new GenerateException("Failed generating users", parseException);
        }
    }

    /**
     * Generate demo events
     *
     * @param eventsRepository The repository in which the events will be generated.
     * @return Returns the amount of event generated.
     * @throws GenerateException Exception thrown, if the generation failed.
     */
    @Transactional
    private int generateEvents(final EventsRepository eventsRepository) throws GenerateException {
        try {
            List<Event> events = getDemoEvents(eventsRepository);
            for (Event event : events) {
                eventsRepository.registerEvent(event);
            }
            return events.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate events", exception);
        }
    }

    @Transactional
    private int generateTicketCategories(final TicketRepository ticketRepository)
            throws GenerateException {
        try {
            List<TicketCategory> ticketCategories = getDemoTicketCategories(eventsRepository);
            for (TicketCategory ticketCategory : ticketCategories) {
                ticketRepository.registerTicketCategory(ticketCategory);
            }
            return ticketCategories.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate ticket categories", exception);
        }
    }

    @Transactional
    private int generateTickets(final UsersRepository usersRepository,
                                final TicketRepository ticketRepository)
            throws GenerateException {
        try {
            List<Ticket> tickets = getDemoTickets(usersRepository, ticketRepository);
            for (Ticket ticket : tickets) {
                ticketRepository.persistTicket(ticket);
            }
            return tickets.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate ticket categories", exception);
        }
    }

    @Transactional
    private int generateLocations(EventsRepository eventsRepository) throws GenerateException {
        try {
            List<Location> locations = this.getDemoLocations();
            for (Location location : locations) {
                eventsRepository.addLocation(location);
            }
            return locations.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate locations", exception);
        }
    }

    private List<User> getDemoUsers() throws ParseException {
        final List<User> users = new ArrayList<>(INITIAL_USERS_CAPACITY);
        users.add(new User(null, "silvio.berlusconi@italy.it",
                "Berlusconi", "Silvio", this.dateFormat.parse("1950-09-11"), Gender.MALE));
        users.add(new User(null, "i.beller@cervelat.de",
                "Beller", "Irina", this.dateFormat.parse("1900-03-12"), Gender.FEMALE));
        users.add(new User(null, "franz.becki@idc.yolo",
                "Beckenbauer", "Franz Anton", this.dateFormat.parse("1945-09-11"), Gender.MALE));
        users.add(new User(null, "g.n@netz.los",
                "Netzer", "Günther", this.dateFormat.parse("1944-09-14"), Gender.MALE));

        return users;
    }

    private List<Event> getDemoEvents(final EventsRepository eventsRepository) {
        final Location kaufleuten = eventsRepository.getLocationById(1);
        final Location plaza = eventsRepository.getLocationById(2);
        final List<Event> events = new ArrayList<>(INITIAL_EVENTS_CAPACITY);
        events.add(new Event(null, "Bobba Fett Party",
                "Sei wie Bobba. Sei Fett.", kaufleuten));
        events.add(new Event(null, "Nachtseminar",
                "DIE Party für Studis", plaza));
        events.add(new Event(null, "Duschi Abgstellt Party",
                "Party für Fussballer nach dem Duschen", kaufleuten));
        events.add(new Event(null, "Silvios Bunga Bunga Party",
                "Silvios exklusive Party für die 'gehobene' Gesellschaft", kaufleuten));
        return events;
    }

    private List<TicketCategory> getDemoTicketCategories(final EventsRepository eventsRepository) throws ParseException {
        final Event bobbaFettParty = eventsRepository.getEventById(1);
        final Event studiParty = eventsRepository.getEventById(2);
        final Event duschiParty = eventsRepository.getEventById(3);
        final Event silviosParty = eventsRepository.getEventById(4);
        final List<TicketCategory> ticketCategories = new ArrayList<>(INITIAL_TICKET_CATEGORY_CAPACITY);

        /* Bobba Fett Party */
        ticketCategories.add(new TicketCategory(null,
                "Vorverkauf", "Das Vorverkaufsticket der Extraklasse",
                bobbaFettParty, 15.00, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null,
                "Abendkasse", "Das übliche Ticket an der Abendkasse",
                bobbaFettParty, 25.00, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));

        /* Studi Party */
        ticketCategories.add(new TicketCategory(null,
                "Early Bird", "Wenn du vor 12 Uhr kommst, erhälst du gratis Eintritt.",
                studiParty, 0.0, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null,
                "Abendkasse", "Nach 12 Uhr musst du für den " +
                "Eintritt bezahlen. Aber immernoch Studipreis :)",
                studiParty, 5.00, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));

        /* Duschi Party */
        ticketCategories.add(new TicketCategory(null,
                "Stürmer Ticket", "Wenn du ein Stürmer bist. Inklusive Salat.",
                duschiParty, 5.00, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null,
                "Mittelfeld Ticket",
                "Für Mittelfeldspieler. Inklusive Rüebli.",
                duschiParty, 10.00, dateFormat.parse("2017-4-20"),
                dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null, "Verteidiger Ticket",
                "Die Verteidiger müssen standhaft sein. Inklusive Cervelat",
                duschiParty, 15.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null, "Goalie Ticket",
                "Flinke Hände. Red Bull Inklusive", duschiParty,
                20.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20")));

        /* Silvios Party */
        ticketCategories.add(new TicketCategory(null, "Silvios Freundschaft",
                "Wenn du mit Silvio befreundet bist.", silviosParty,
                3455.00, dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20")));
        ticketCategories.add(new TicketCategory(null, "Nur Bekannter Ticket",
                "Nur ein Bekannter des Presidente.", silviosParty, 6545.00,
                dateFormat.parse("2017-4-20"), dateFormat.parse("2017-5-20")));
        return ticketCategories;
    }

    private List<Ticket> getDemoTickets(final UsersRepository usersRepository, final TicketRepository ticketRepository) {
        final List<Ticket> tickets = new ArrayList<>(INITIAL_TICKET_CAPACITY);

        /* Users */
        final User silvio = usersRepository.getUserById(1);
        final User irina = usersRepository.getUserById(2);
        final User franz = usersRepository.getUserById(3);
        final User guenther = usersRepository.getUserById(4);

        /* Categories */
        final TicketCategory bobbaFett1 = ticketRepository.getTicketCategoryById(1);
        final TicketCategory bobbaFett2 = ticketRepository.getTicketCategoryById(2);

        final TicketCategory studi1 = ticketRepository.getTicketCategoryById(3);
        final TicketCategory studi2 = ticketRepository.getTicketCategoryById(4);

        final TicketCategory duschi1 = ticketRepository.getTicketCategoryById(5);
        final TicketCategory duschi2 = ticketRepository.getTicketCategoryById(6);
        final TicketCategory duschi3 = ticketRepository.getTicketCategoryById(7);
        final TicketCategory duschi4 = ticketRepository.getTicketCategoryById(8);

        final TicketCategory silvio1 = ticketRepository.getTicketCategoryById(9);
        final TicketCategory silvio2 = ticketRepository.getTicketCategoryById(10);

        /* Bobba Fett Party */
        tickets.add(bobbaFett1.sellTicket(silvio));

        tickets.add(bobbaFett2.sellTicket(franz));

        /* Studi Party */
        tickets.add(studi1.sellTicket(irina));
        tickets.add(studi1.sellTicket(guenther));
        tickets.add(studi1.sellTicket(silvio));

        tickets.add(studi2.sellTicket(franz));

        /* Duschi Party */
        tickets.add(duschi1.sellTicket(guenther));

        return tickets;
    }

    private List<Location> getDemoLocations() {
        List<Location> locations = new ArrayList<>(INITIAL_LOCATION_CAPACITY);

        locations.add(new Location(null,
                "Kaufleuten",
                "Der beste Klub der Schweiz.",
                "ChIJ7ZMwUgEKkEcRjV6Q7jc2y1I"));
        locations.add(new Location(null,
                "Plaza",
                "Der andere beste Klub der Schweiz",
                "ChIJIXJ33hsKkEcRTTvRa3eNxd0"));

        return locations;
    }
}
