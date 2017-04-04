package rest;

import com.fasterxml.jackson.databind.JsonNode;
import models.events.Event;
import demoData.DemoData;
import play.libs.Json;
import play.mvc.*;

import java.util.Optional;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 * Controls the REST Event-Requests.
 */
public class EventsController extends Controller {
    private DemoData demoData;

    public EventsController() {
        this.demoData = DemoData.getInstance();
    }



    public Result getAllEvents() {
        JsonNode jsonNode = Json.toJson(demoData.getEvents());
        return ok(jsonNode);
    }

    public Result getEvent(int id) {
        Optional<Event> maybeEvent = demoData.getEvents().stream()
                .filter(event -> event.getId() == id)
                .findFirst();

        if(maybeEvent.isPresent()) {
            return ok(Json.toJson(maybeEvent.get()));
        } else {
            return Results.notFound();
        }
    }

    public Result getTicket(Integer userId, Integer eventId){
        String result = "User: " + userId + "Ticket: " + eventId;
        return ok(Json.toJson(result));
    }
}
