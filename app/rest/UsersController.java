package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.users.UsersLogic;
import models.tickets.SoldTicket;
import models.tickets.TicketInstance;
import models.users.User;
import demoData.DemoData;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Fabian on 28.03.17.
 * Controller for /users
 */
public class UsersController extends Controller {
    private DemoData demoData;
    private UsersLogic usersLogic;

    @Inject
    public UsersController(UsersLogic usersLogic){
        this.demoData = DemoData.getInstance();
        this.usersLogic = usersLogic;
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
        return notFound("Ticket not found");
    }

    public Result getEvents(Integer userId){
        Optional<ArrayList<TicketInstance>> optionalTickets = demoData.getUsers().stream()
                .filter(user -> userId == user.getId())
                .map(user -> user.getTickets())
                .findFirst();

        return ok(Json.toJson(optionalTickets.get()));
    }

    @Transactional
    public Result getUser(Integer userId){

        User user = this.usersLogic.getUserById(userId);
        if(user != null) {
            user.addTickets(demoData.getUsers().get(userId-1).getTickets());
            JsonNode jsonUser = Json.toJson(user);
            return ok(Json.toJson(jsonUser));
        }

        return notFound("User not found");
    }
}
