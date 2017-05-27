package dal.generator;

import dal.coatchecks.CoatChecksRepository;
import dal.events.EventsRepository;
import dal.tickets.TicketRepository;
import dal.users.UsersRepository;
import models.events.CoatHanger;
import models.events.Event;
import models.events.Location;
import models.events.TicketCategory;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.users.Gender;
import models.users.User;
import play.Logger;
import play.db.jpa.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.DateGenerator.generateDate;

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
    private static final int INITIAL_COAT_HANGER_CAPACITY = 10;
    private static final int INITIAL_COAT_CHECK_CAPACITY = 2;

    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;
    private final TicketRepository ticketRepository;
    private final CoatChecksRepository coatChecksRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
                         final TicketRepository ticketRepository,
                         final CoatChecksRepository coatChecksRepository) {
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
        this.ticketRepository = ticketRepository;
        this.coatChecksRepository = coatChecksRepository;
    }

    /**
     * This method initializes the data for the repositories.
     */
    public void initializeData() throws GenerateException {
        confirmRepositoryNotNull(this.usersRepository,
                this.eventsRepository,
                this.ticketRepository,
                this.coatChecksRepository);

        final int amountOfLocations = generateLocations(this.eventsRepository);
        final int amountOfUsers = generateUsers(this.usersRepository);
        final int amountOfEvents = generateEvents(this.eventsRepository);
        final int amountOfTicketCategories = generateTicketCategories(this.ticketRepository);
        final int amountOfTickets = generateTickets(this.usersRepository,
                this.ticketRepository);
        final int amountOfCoatHangers = generateCoatHangers(this.coatChecksRepository);
        final int amountOfCoatChecks = generateCoatChecks(this.coatChecksRepository);

        Logger.info("Generated " + amountOfUsers + " users");
        Logger.info("Generated " + amountOfEvents + " events");
        Logger.info("Generated " + amountOfTicketCategories + " ticketCategories");
        Logger.info("Generated " + amountOfTickets + " tickets");
        Logger.info("Generated " + amountOfLocations + " locations");
        Logger.info("Generated " + amountOfCoatHangers + " CoatHangers");
        Logger.info("Generated " + amountOfCoatChecks + " CoatChecks");
    }

    /**
     * Checks if repositories are null.
     *
     * @param repositories Repositories that can't be null
     * @param <T> repository
     * @throws GenerateException Exception thrown, if the generation failed.
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
            throw new GenerateException("Failed to generate tickets", exception);
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

    @Transactional
    private int generateCoatHangers(CoatChecksRepository coatChecksRepository) throws GenerateException {
        try {
            List<CoatHanger> coatHangers = this.getDemoCoatHangers();
            for (CoatHanger c : coatHangers) {
                coatChecksRepository.addNewCoatHanger(c);
            }
            return coatHangers.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate CoatHangers", exception);
        }
    }

    @Transactional
    private int generateCoatChecks(CoatChecksRepository coatChecksRepository) throws GenerateException {
        try {
            List<CoatCheck> coatChecks = this.getDemoCoatChecks();
            for (CoatCheck c : coatChecks) {
                coatChecksRepository.addNewCoatCheck(c);
            }
            return coatChecks.size();
        } catch (Exception exception) {
            throw new GenerateException("Failed to generate CoatChecks", exception);
        }
    }

    private List<User> getDemoUsers() throws ParseException {
        final String berlusconi = "berlusconi.jpg";
        final String beller = "beller.jpg";
        final String beckenbauer = "beckenbauer.jpg";
        final String netzer = "netzer.jpg";
        final String klitschkoWladimir = "klitschkoWladimir.jpg";
        final String klitschkoVitali = "KlitschkoVitali.jpg";
        final String gavric = "gavric.jpg";


        final List<User> users = new ArrayList<>(INITIAL_USERS_CAPACITY);
        users.add(new User(null, "silvio.berlusconi@italy.it",
                "Berlusconi", "Silvio", this.dateFormat.parse("1950-09-11"), Gender.MALE, "123456", false, berlusconi));
        users.add(new User(null, "i.beller@cervelat.de",
                "Beller", "Irina", this.dateFormat.parse("1900-03-12"), Gender.FEMALE,"123456", false, beller));
        users.add(new User(null, "franz.becki@idc.yolo",
                "Beckenbauer", "Franz Anton", this.dateFormat.parse("1945-09-11"), Gender.MALE,"123456", false, beckenbauer));
        users.add(new User(null, "g.n@netz.los",
                "Netzer", "Günther", this.dateFormat.parse("1944-09-14"), Gender.MALE,"123456", false, netzer));
        users.add(new User(null, "wladimir.klitschko@plaza.ch",
                "Klitschko", "Wladimir", this.dateFormat.parse("1976-03-25"), Gender.MALE, "123456", true, klitschkoWladimir));
        users.add(new User(null, "vitali.klitschko@kauf.ch",
                "Klitschko", "Vitali", this.dateFormat.parse("1971-06-19"), Gender.MALE, "123456", true, klitschkoVitali));
        users.add(new User(null, "bachelor@kauf.ch",
                "Gavric", "Vujo", this.dateFormat.parse("1990-06-19"), Gender.MALE, "123456", true, gavric));




        return users;
    }


    private List<Event> getDemoEvents(final EventsRepository eventsRepository) throws ParseException {

        final String pictureKaufleuten = "kaufleuten.png";
        final String picturePlaza = "nachtseminar.png";
        final String pictureSilvio = "bunga-bunga.jpg";
        final String pictureShower = "shower.png";

        final Location kaufleuten = eventsRepository.getLocationById(1);
        final Location plaza = eventsRepository.getLocationById(2);
        final List<Event> events = new ArrayList<>(INITIAL_EVENTS_CAPACITY);
        events.add(new Event(null, "Bobba Fett Party",
                "Sei wie Bobba. Sei Fett.", kaufleuten, generateDate(12, 22,0), pictureKaufleuten));
        events.add(new Event(null, "Nachtseminar",
                "DIE Party für Studis", plaza, generateDate(14, 21,0), picturePlaza));
        events.add(new Event(null, "Duschi Abgstellt Party",
                "Party für Fussballer nach dem Duschen", kaufleuten, generateDate(-2,20,0), pictureShower));
        events.add(new Event(null, "Silvios Bunga Bunga Party",
                "Silvios exklusive Party für die 'gehobene' Gesellschaft",
                kaufleuten, generateDate(100,19,0), pictureSilvio));




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
                bobbaFettParty, 15.00, generateDate(0),
                generateDate(10)));
        ticketCategories.add(new TicketCategory(null,
                "Abendkasse", "Das übliche Ticket an der Abendkasse",
                bobbaFettParty, 25.00, generateDate(10),
                generateDate(13,2,0)));

        /* Studi Party */
        ticketCategories.add(new TicketCategory(null,
                "Early Bird", "Vorverkaufsticket",
                studiParty, 0.0, generateDate(0),
                generateDate(13)));
        ticketCategories.add(new TicketCategory(null,
                "Abendkasse", "Nach 12 Uhr musst du für den " +
                "Eintritt bezahlen. Aber immernoch Studipreis :)",
                studiParty, 5.00, generateDate(13),
                generateDate(14, 2,0)));

        /* Duschi Party */
        ticketCategories.add(new TicketCategory(null,
                "Stürmer Ticket", "Wenn du ein Stürmer bist. Inklusive Salat.",
                duschiParty, 5.00, generateDate(-10),
                generateDate(-1,1,0)));
        ticketCategories.add(new TicketCategory(null,
                "Mittelfeld Ticket",
                "Für Mittelfeldspieler. Inklusive Rüebli.",
                duschiParty, 10.00, generateDate(-10),
                generateDate(-1,1,0)));
        ticketCategories.add(new TicketCategory(null, "Verteidiger Ticket",
                "Die Verteidiger müssen standhaft sein. Inklusive Cervelat",
                duschiParty, 15.00, generateDate(-10), generateDate(-1,1,0)));
        ticketCategories.add(new TicketCategory(null, "Goalie Ticket",
                "Flinke Hände. Red Bull Inklusive", duschiParty,
                20.00, generateDate(-10), generateDate(-1,1,0)));

        /* Silvios Party */
        ticketCategories.add(new TicketCategory(null, "Silvios Freundschaft",
                "Wenn du mit Silvio befreundet bist.", silviosParty,
                3455.00, generateDate(0), generateDate(99)));
        ticketCategories.add(new TicketCategory(null, "Nur Bekannter Ticket",
                "Nur ein Bekannter des Presidente.", silviosParty, 6545.00,
                generateDate(0), generateDate(99)));
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
        tickets.add(duschi2.sellTicket(silvio));
        tickets.add(duschi3.sellTicket(franz));
        tickets.add(duschi4.sellTicket(irina));

        tickets.add(silvio1.sellTicket(silvio));
        tickets.add(silvio1.sellTicket(franz));
        tickets.add(silvio2.sellTicket(irina));

        return tickets;
    }

    private List<CoatHanger> getDemoCoatHangers(){
        List<CoatHanger> coatHangers = new ArrayList<>(INITIAL_COAT_HANGER_CAPACITY);

        Location kaufleuten = eventsRepository.getLocationByName("Kaufleuten");
        Location plaza = eventsRepository.getLocationByName("Plaza");

        coatHangers.add(new CoatHanger(null, 1, kaufleuten));
        coatHangers.add(new CoatHanger(null, 2, kaufleuten));
        coatHangers.add(new CoatHanger(null, 3, kaufleuten));
        coatHangers.add(new CoatHanger(null, 4, kaufleuten));
        coatHangers.add(new CoatHanger(null, 5, kaufleuten));

        coatHangers.add(new CoatHanger(null, 1, plaza));
        coatHangers.add(new CoatHanger(null, 2, plaza));
        coatHangers.add(new CoatHanger(null, 3, plaza));
        coatHangers.add(new CoatHanger(null, 4, plaza));
        coatHangers.add(new CoatHanger(null, 5, plaza));

        return coatHangers;
    }

    private List<CoatCheck> getDemoCoatChecks(){
        List<CoatCheck> coatChecks = new ArrayList<>(INITIAL_COAT_CHECK_CAPACITY);

        CoatHanger kaufleutenHanger2 = coatChecksRepository.getCoatHangerByNumberAndLocationID(2, "ChIJ7ZMwUgEKkEcRjV6Q7jc2y1I");
        CoatHanger plazaHanger2 = coatChecksRepository.getCoatHangerByNumberAndLocationID(2, "ChIJIXJ33hsKkEcRTTvRa3eNxd0");
        CoatHanger plazaHanger3 = coatChecksRepository.getCoatHangerByNumberAndLocationID(3, "ChIJIXJ33hsKkEcRTTvRa3eNxd0");
        CoatHanger plazaHanger4 = coatChecksRepository.getCoatHangerByNumberAndLocationID(4, "ChIJIXJ33hsKkEcRTTvRa3eNxd0");
        User user1 = usersRepository.getUserByEmail("i.beller@cervelat.de");
        User user2 = usersRepository.getUserByEmail("silvio.berlusconi@italy.it");

        CoatCheck c1 = new CoatCheck(kaufleutenHanger2, new Date(), user1);
        CoatCheck c2 = new CoatCheck(plazaHanger2, new Date(), user2);
        CoatCheck c3 = new CoatCheck(plazaHanger3, new Date(), user2);
        CoatCheck c4 = new CoatCheck(plazaHanger4, new Date(), user2);

        c1.setPublicIdentifier(654321);
        c2.setPublicIdentifier(123456);
        c4.fetch(new Date());

        coatChecks.add(c1);
        coatChecks.add(c2);
        coatChecks.add(c3);
        coatChecks.add(c4);

        user2.setCoatChecks(coatChecks);

        return coatChecks;
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
