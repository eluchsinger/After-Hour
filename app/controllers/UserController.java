package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dataLayer.events.Event;
import dataLayer.tickets.SoldTicket;
import dataLayer.tickets.TicketInstance;
import dataLayer.users.User;
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
    private JPAApi jpaApi;

    @Inject
    public UserController(JPAApi jpaApi){
        this.demoData = DemoData.getInstance();
        this.jpaApi = jpaApi;
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

    @Transactional(readOnly = true)
    public Result getUser(Integer userId){

        EntityManager em = jpaApi.em();
        User user = em.find(User.class, userId);
        if(user != null) {
            JsonNode jsonUser = Json.toJson(user);
            return ok(Json.toJson(jsonUser));
        }

        return notFound("User not found");

//        Optional<User> optionalUser = demoData.getUsers().stream()
//                .filter(user -> user.getId() == userId)
//                .findFirst();
//
//        if(optionalUser.isPresent()){
//            JsonNode user = Json.toJson(optionalUser.get());
//            return ok(Json.toJson(user));
//        }
//        return notFound("User not found");
    }
}
