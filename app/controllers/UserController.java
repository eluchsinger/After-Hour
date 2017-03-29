package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Fabian on 28.03.17.
 * Controller for /users
 */
public class UserController extends Controller {

    public Result getTicket(Integer userId, Integer eventId){
        String result = "User: " + userId + " Ticket: " + eventId;
        return ok(Json.toJson(result));
    }
}
