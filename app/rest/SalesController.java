package rest;

import logic.sales.SalesLogic;
import models.exceptions.ServerException;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by Fabian on 08.04.17.
 */
public class SalesController extends Controller {
    private SalesLogic salesLogic;

    @Inject
    public SalesController(SalesLogic salesLogic){
        this.salesLogic = salesLogic;
    }

    @Transactional
    public Result buyTicket(Integer userId, Integer ticketCategory){
        try {
            return ok(Json.toJson(salesLogic.buyTicket(userId, ticketCategory)));
        } catch (ServerException e){
            return badRequest(Json.toJson(e));
        }
    }
}
