package db;

import play.db.Database;
import play.mvc.Controller;

import javax.inject.Inject;

/**
 * Created by Esteban Luchsinger on 03.04.2017.
 */
public class JavaApplicationDatabase extends Controller {
    private Database db;

    @Inject
    public JavaApplicationDatabase(Database db){
        this.db = db;
    }
}
