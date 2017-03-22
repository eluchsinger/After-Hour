package controllers;

import play.mvc.*;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 * Controls the REST Event-Requests.
 */
public class EventsController extends Controller {

    public Result getAllEvents() {
        return ok("Kaufleuten Event");
    }

}
