package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Event;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 * Controls the REST Event-Requests.
 */
public class EventsController extends Controller {

    public final List<Event> events;
    public EventsController() {
        this.events = new ArrayList<Event>();
        this.events.add(new Event(1, "Bobba Fett Party", "Sei wie Bobba. Sei Fett."));
        this.events.add(new Event(2, "Nachtseminar", "DIE Party für Studis"));
    }

    public Result getAllEvents() {
        JsonNode jsonNode = Json.toJson(this.events);
        return ok(jsonNode);
    }

}
