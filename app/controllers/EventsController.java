package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dataLayer.events.Event;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 * Controls the REST Event-Requests.
 */
public class EventsController extends Controller {

    public final List<Event> events;
    public EventsController() {
        this.events = new ArrayList<>();
        this.initEvents();
    }

    private void initEvents() {
        this.events.add(new Event(1, "Bobba Fett Party", "Sei wie Bobba. Sei Fett."));
        this.events.add(new Event(2, "Nachtseminar", "DIE Party für Studis"));
        this.events.add(new Event (3, "Duschi Abgstellt Party", "Party für Fussballer nach dem Duschen"));
    }

    public Result getAllEvents() {
        JsonNode jsonNode = Json.toJson(this.events);
        return ok(jsonNode);
    }

    public Result getEvent(int id) {
        Optional<Event> maybeEvent = this.events.stream()
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
