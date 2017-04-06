package admin;

import play.mvc.Controller;
import play.mvc.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by Fabian on 06.04.17.
 */
public class AdminController extends Controller {

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
