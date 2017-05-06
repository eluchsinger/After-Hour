package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.events.EventsLogic;
import models.events.Event;
import play.api.Play;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 * Controls the REST Event-Requests.
 */
public class EventsController extends Controller {
    private EventsLogic eventsLogic;

    @Inject
    public EventsController(EventsLogic eventsLogic) {
        this.eventsLogic = eventsLogic;
    }


    @Transactional
    public Result getAllEvents() {
        JsonNode jsonNode = Json.toJson(eventsLogic.getEvents());
        return ok(jsonNode);
    }

    @Transactional
    public Result getEvent(Integer id) {
        Event event = eventsLogic.getEventById(id);
        if (event != null){
            return ok(Json.toJson(event));
        }
        return badRequest("Ticket not found");
    }

    public Result getTicket(Integer userId, Integer eventId){
        String result = "User: " + userId + "Ticket: " + eventId;
        return ok(Json.toJson(result));
    }

    public Result getEventImage(Integer eventId){
        BufferedImage img;
        File file = Play.current().getFile("app/pictures/fab.jpg");
        return ok(file);
    }
}
