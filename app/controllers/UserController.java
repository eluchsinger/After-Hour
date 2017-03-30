package controllers;

import dataLayer.events.Event;
import dataLayer.tickets.SoldTicket;
import dataLayer.tickets.TicketInstance;
import dataLayer.users.User;
import demoData.DemoData;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Optional;

/**
 * Created by Fabian on 28.03.17.
 * Controller for /users
 */
public class UserController extends Controller {
    private DemoData demoData;

    public UserController(){
        this.demoData = DemoData.getInstance();
    }


    public Result getTicket(Integer userId, Integer eventId){
        Optional<User> optinalUser = demoData.getUsers().stream()
                .filter(user -> user.getId() == userId)
                .findFirst();

        if(optinalUser.isPresent()){
            Optional<SoldTicket> optionalTicket = optinalUser.get().getTickets().stream()
                    .filter(ticketInstance -> ticketInstance instanceof SoldTicket)
                    .map(ticket -> (SoldTicket) ticket)
                    .filter(ticket -> ticket.getEvent().getId() == eventId)
                    .findFirst();
            if (optionalTicket.isPresent()){
                //Todo: Antwort spezifzieren
                return ok("Ticket found");
            }
        }
        return ok(Json.toJson("No Ticket"));
    }
}
