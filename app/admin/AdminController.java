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

    public Result executeUpdate2(){
        StringBuffer output = new StringBuffer();

        Process process;
        try {
            process = Runtime.getRuntime().exec("git pull");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ok(output.toString());
    }

    public Result executeUpdate(){

        ProcessBuilder pb = new ProcessBuilder("conf/update.sh");

        try {
            Process p = pb.start();
        } catch (IOException e) {
            return ok(e.getMessage());
        }

        return ok("Update");
    }
}
