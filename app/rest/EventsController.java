package rest;

import logic.events.EventsLogic;
import models.events.Event;
import models.exceptions.EventDoesNotExistException;
import models.exceptions.ServerException;
import play.api.Play;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
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
        return ok(Json.toJson(eventsLogic.getEvents()));
    }

    @Transactional
    public Result getEvent(Integer id) {
        try {
            return ok(Json.toJson(eventsLogic.getEventById(id)));
        } catch (EventDoesNotExistException e) {
            return badRequest(Json.toJson(e));
        }
    }

    @Transactional
    public Result getTicketCategories(Integer eventId, Boolean available){
        try {
            return ok(Json.toJson(eventsLogic.getEventWithTicketCategories(eventId, available)));
        } catch (ServerException e){
            return badRequest(Json.toJson(e));
        }

    }

    @Transactional
    public Result getEventImage(Integer eventId){
        try {
            final Event event = eventsLogic.getEventById(eventId);
            File file = Play.current().getFile("app/pictures/" + event.getPictureName());
            return ok(file);
        } catch (EventDoesNotExistException e) {
            return badRequest(Json.toJson(e));
        }
    }
}
