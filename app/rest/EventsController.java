package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.events.EventsLogic;
import models.events.Event;
import play.Logger;
import play.api.Play;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.*;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

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

        try {
            File file = Play.current().getFile("app/pictures/fab.jpg");
            img = ImageIO.read(file);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return ok(bytes);
        } catch (IOException e) {
            Logger.error("Tried to Read Image: " + e.getMessage());
        }
        return badRequest();

    }
}
