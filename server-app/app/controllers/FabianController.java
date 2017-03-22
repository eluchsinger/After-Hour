package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Created by Esteban Luchsinger on 22.03.2017.
 */
public class FabianController extends Controller {
    public Result yolo() {
        return ok(index.render("Hoi Fabian. Hesch d' Duschi abgstellt?"));
    }

}
