package rest;

import com.fasterxml.jackson.databind.JsonNode;
import demoData.DemoData;
import logic.users.UsersLogic;
import models.users.User;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class UsersController extends Controller {
    private DemoData demoData;
    private UsersLogic usersLogic;

    @Inject
    public UsersController(UsersLogic usersLogic){
        this.demoData = DemoData.getInstance();
        this.usersLogic = usersLogic;
    }


    public Result getTicket(Integer userId, Integer eventId){
        return notFound("Not implemented");
    }

    public Result getEvents(Integer userId){
        return notFound("Not implemented");
    }

    public Result registerUser(){
        JsonNode json = request().body().asJson();
        User user = createUserFromJson(json);
        return ok(Json.toJson(user));
    }

    private User createUserFromJson(JsonNode json){
        return Json.fromJson(json, User.class);
    }

    @Transactional
    public Result getUser(Integer userId){
        final User user = this.usersLogic.getUserById(userId);
        if(user != null) {
            JsonNode jsonUser = Json.toJson(user);
            return ok(Json.toJson(jsonUser));
        }

        return notFound("User not found");
    }
}
