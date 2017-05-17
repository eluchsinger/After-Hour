package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.users.UsersLogic;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistException;
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

    @Transactional
    public Result getTicket(Integer userId, Integer eventId){
        try {
            return ok(Json.toJson(usersLogic.getTicket(userId, eventId)));
        } catch (ServerException e){
            return badRequest(Json.toJson(e));
        }

    }

    @Transactional
    public Result login(){
        try {
            Map<String, String[]> loginData = request().body().asFormUrlEncoded();

            String email = loginData.get("email")[0];
            email = replaceCharacterEntities(email);
            String password = loginData.get("password")[0];

            return ok(Json.toJson(usersLogic.login(email,password)));
        } catch (ServerException e){
            return badRequest(Json.toJson(e));
        } catch (NullPointerException e){
            return badRequest("Empty Body");
        }

    }

    @Transactional
    public Result getUserByEmail(String email){
        try {
            email = replaceCharacterEntities(email);
            User user = this.usersLogic.getUserByEmail(email);
            return ok(Json.toJson(user));
        } catch (UserDoesNotExistException e) {
            return badRequest(Json.toJson(e));
        }


    }

    @Transactional
    public Result registerUser(){
        try {
            JsonNode json = request().body().asJson();
            User user = Json.fromJson(json, User.class);
            this.usersLogic.registerUser(user);
            return ok();
        } catch (Exception e) {
            return badRequest("Registration Process couldn't start");
        }

    }

    @Transactional
    public Result getUser(Integer userId) {
        try {
            final User user = this.usersLogic.getUserById(userId);
            return ok(Json.toJson(user));
        } catch (UserDoesNotExistException e) {
            return badRequest(Json.toJson(e));
        }
    }

    private String replaceCharacterEntities(String email) {
        if(email.contains("%40")){
            email = email.replace("%40", "@");
        }
        return email;
    }
}
