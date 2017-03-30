package demoData;

import dataLayer.events.EntryTicket;
import dataLayer.events.Event;
import dataLayer.events.TicketCategory;
import dataLayer.tickets.SoldTicket;
import dataLayer.users.Gender;
import dataLayer.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Fabian on 29.03.2017.
 * Singleton to create Demo Data -> Accessible from all Controllers.
 */
public class DemoData {
    private static DemoData ourInstance = new DemoData();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private SimpleDateFormat dateFormat;

    public static DemoData getInstance() {
        return ourInstance;
    }

    private DemoData() {
        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        initEvents();
        initUsers();
        initTicketCategories();
        initSoldTickets();
    }

    private void initEvents() {
        this.events.add(new Event(1, "Bobba Fett Party", "Sei wie Bobba. Sei Fett."));
        this.events.add(new Event(2, "Nachtseminar", "DIE Party für Studis"));
        this.events.add(new Event (3, "Duschi Abgstellt Party", "Party für Fussballer nach dem Duschen"));
        this.events.add(new Event(4,"Silvios Bunga Bunga Party", "Silvios exklusive Party für die 'gehobene' Gesellschaft)"));
    }

    public ArrayList<Event> getEvents(){
        return events;
    }

    private void initUsers(){
        try {
            this.users.add(new User(1, "Berlusconi","Silvio", dateFormat.parse("1950-09-11"), Gender.MALE));
            this.users.add(new User(2, "Beller", "Irina", dateFormat.parse("1900-03-12"), Gender.FEMALE));
            this.users.add(new User(3,"Beckenbauer", "Franz Anton",dateFormat.parse("1945-09-11"), Gender.MALE));
            this.users.add(new User(4, "Netzer", "Günther", dateFormat.parse("1944-09-14"), Gender.MALE));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    private void initTicketCategories(){
        try {
            events.get(0).addTicketCategory(new EntryTicket("Ticket", 25, dateFormat.parse("2017-03-30"), dateFormat.parse("2017-04-20")));
            events.get(1).addTicketCategory(new EntryTicket("StudentenTicket", 5, dateFormat.parse("2017-03-30"), dateFormat.parse("2017-05-20")));
            events.get(1).addTicketCategory(new EntryTicket("Ticket", 50, dateFormat.parse("2017-03-30"), dateFormat.parse("2017-05-20")));
            events.get(2).addTicketCategory(new EntryTicket("Ticket", 15, dateFormat.parse("2017-03-30"), dateFormat.parse("2017-06-20")));
            events.get(3).addTicketCategory(new EntryTicket("Ticket", 10, dateFormat.parse("2017-03-30"), dateFormat.parse("2017-08-20")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initSoldTickets(){
        soldTicket(1,1,0);
        soldTicket(2,1,0);
        soldTicket(2,2,0);
    }

    private void soldTicket(int userId, int eventId, int ticketCategory){
        //Sample Ticket sold, with all references.
        SoldTicket soldTicket = new SoldTicket(users.get(userId - 1), events.get(eventId - 1), events.get(eventId - 1).getTicketCategories().get(ticketCategory));
        TicketCategory category = events.get(eventId - 1).getTicketCategories().get(ticketCategory);
        category.addSoldTicket(soldTicket);
        users.get(userId - 1).addTicket(soldTicket);
    }

}