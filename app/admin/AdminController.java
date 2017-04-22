package admin;

import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;

/**
 * Created by Fabian Schwyter on 06.04.17.
 * Provides important administration logic.
 */
public class AdminController extends Controller {

    /**
     * Executes the update script to update the server.
     * @return Returns the result of the update.
     */
    public Result executeUpdate(){
        ProcessBuilder pb = new ProcessBuilder("conf/update.sh");

        try {
            pb.start();
        } catch (IOException e) {
            return internalServerError(e.getMessage());
        }

        return ok("Update");
    }
}
