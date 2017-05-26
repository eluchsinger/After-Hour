package rest;

import com.fasterxml.jackson.databind.JsonNode;
import logic.users.UsersLogic;
import models.exceptions.ServerException;
import models.exceptions.UserDoesNotExistServerException;
import models.users.User;
import play.api.Play;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
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
    public Result getAvailableEvents(Integer userId){
        try {
            return ok(Json.toJson(usersLogic.getEventsAvailable(userId)));
        } catch (UserDoesNotExistServerException e) {
            return badRequest(Json.toJson(e));
        }
    }

    @Transactional
    public Result login(){
        try {
            Map<String, String[]> loginData = request().body().asFormUrlEncoded();

            String email = replaceCharacterEntities(loginData.get("email")[0]);
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
        String emailWithoutEntity;
        try {
            emailWithoutEntity = replaceCharacterEntities(email);
            User user = this.usersLogic.getUserByEmail(emailWithoutEntity);
            return ok(Json.toJson(user));
        } catch (UserDoesNotExistServerException e) {
            return badRequest(Json.toJson(e));
        }
    }

    @Transactional
    public Result registerUser(){
        try {
            JsonNode json = request().body().asJson();
            User user = Json.fromJson(json, User.class);
            this.usersLogic.registerUser(user);
            user = this.usersLogic.getUserByEmail(user.getEmail());
            return ok(Json.toJson(user));
        } catch (UserDoesNotExistServerException e){
            return badRequest(Json.toJson(e));
        }
        catch (Exception e) {
            return badRequest("Registration Process couldn't start");
        }
    }

    @Transactional
    public Result getUser(Integer userId) {
        try {
            final User user = this.usersLogic.getUserById(userId);
            return ok(Json.toJson(user));
        } catch (UserDoesNotExistServerException e) {
            return badRequest(Json.toJson(e));
        }
    }

    @Transactional
    public Result getProfileImage(Integer userId){
        try {
            final User user = usersLogic.getUserById(userId);
            File file = Play.current().getFile("app/pictures/" + user.getPictureName());
            return ok(file);
        } catch (UserDoesNotExistServerException e) {
            return badRequest(Json.toJson(e));
        }
    }

    private String replaceCharacterEntities(String email) {
        String emailWithoutEntity = email;
        if(email.contains("%40")){
            emailWithoutEntity = email.replace("%40", "@");
        }
        return emailWithoutEntity;
    }
}
