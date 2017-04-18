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

public class UsersController extends Controller {
    private DemoData demoData;
    private UsersLogic usersLogic;

    @Inject
    public UsersController(UsersLogic usersLogic){
        this.demoData = DemoData.getInstance();
        this.usersLogic = usersLogic;
    }


    public Result getTicket(Integer userId, Integer eventId){
<<<<<<< HEAD
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
=======
        return notFound("Not implemented");
    }

    public Result getEvents(Integer userId){
        return notFound("Not implemented");
>>>>>>> refs/remotes/origin/developer
    }

    public Result getUserByEmail(String email){
        User user = this.usersLogic.getUserByEmail(email);
        return ok(Json.toJson(user));
    }

    @Transactional
    public Result registerUser(){
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        this.usersLogic.registerUser(user);
        return ok();
    }

    @Transactional
    public Result getUser(Integer userId){
        final User user = this.usersLogic.getUserById(userId);
        if(user != null) {
<<<<<<< HEAD
            user.addTickets(demoData.getUsers().get(userId-1).getTickets());
=======
>>>>>>> refs/remotes/origin/developer
            JsonNode jsonUser = Json.toJson(user);
            return ok(Json.toJson(jsonUser));
        }

        return notFound("User not found");
    }
}
