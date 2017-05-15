package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.users.UsersLogic;
import models.users.User;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;

public class UsersController extends Controller {
    private UsersLogic usersLogic;

    @Inject
    public UsersController(UsersLogic usersLogic){
        this.usersLogic = usersLogic;
    }


    public Result getTicket(Integer userId, Integer eventId){
        return notFound("Not implemented");
    }

    public Result getEvents(Integer userId){
        return notFound("Not implemented");
    }

    @Transactional
    public Result login(){
        Map<String, String[]> loginData = request().body().asFormUrlEncoded();
        String email = loginData.get("email")[0];
        email = replaceCharacterEntities(email);
        User user = this.usersLogic.getUserByEmail(email);
        if (user == null) {
            return badRequest("Incorrect username!");
        }
        if(user.compareWithPassword(loginData.get("password")[0])){
            return ok(Json.toJson(user));
        } else {
            return badRequest("Incorrect password!");
        }
    }

    @Transactional
    public Result getUserByEmail(String email){
        email = replaceCharacterEntities(email);
        User user = this.usersLogic.getUserByEmail(email);
        return ok(Json.toJson(user));
    }

    private String replaceCharacterEntities(String email) {
        if(email.contains("%40")){
            email = email.replace("%40", "@");
        }
        return email;
    }

    @Transactional
    public Result registerUser(){
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        this.usersLogic.registerUser(user);
        return ok();
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
