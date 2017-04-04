package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dal.UsersRepository;
import models.tickets.SoldTicket;
import models.tickets.TicketInstance;
import models.users.User;
import demoData.DemoData;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Fabian on 28.03.17.
 * Controller for /users
 */
public class UserController extends Controller {
    private DemoData demoData;
    private UsersRepository usersRepository;

    @Inject
    public UserController(UsersRepository usersRepository){
        this.demoData = DemoData.getInstance();
        this.usersRepository = usersRepository;
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

        User user = this.usersRepository.getUserById(userId);
        if(user != null) {
            JsonNode jsonUser = Json.toJson(user);
            return ok(Json.toJson(jsonUser));
        }

        return notFound("User not found");
    }
}
