package rest;

import logic.coatChecks.CoatChecksLogic;
import models.events.CoatHanger;
import models.tickets.CoatCheck;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

/**
 * Created by marco on 10.05.2017.
 */
public class CoatController {
    private CoatChecksLogic coatChecksLogic;
    private DateFormat dateFormat;

    @Inject
    public CoatController(CoatChecksLogic coatChecksLogic){
        this.coatChecksLogic = coatChecksLogic;
        this.dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    }

    public Result handOverJacket(String email, Integer coatHangerNumber, String locationName) {
        CoatCheck coatCheck = coatChecksLogic.createNewCoatCheck(email, coatHangerNumber, locationName);
        return coatCheck != null ? ok(Json.toJson(coatCheck)) : badRequest("CoatCheck creation failed");
    }

    public Result fetchJacket(String fetchedOn, Integer coatCheckPublicID) {
        Date date;
        try {
            date = dateFormat.parse(fetchedOn);
        } catch (ParseException e) {
            return badRequest("Bad date String");
        }

        CoatHanger hanger = coatChecksLogic.fetchJacket(date, new Integer(coatCheckPublicID));
        return hanger == null ? ok(Json.toJson(hanger)) : notFound("CoatCheck not found or jacket alread Fetched");
    }
}
