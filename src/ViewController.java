import spark.Request;
import spark.Response;
import util.Path;
import util.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 12/19/17.
 */
public class ViewController {
    public static String serveSelectionPage(Request request, Response response, ArrayList<Team> teams) {

        Map<String, Object> model = new HashMap<>();

        model.put("teams", teams);

        return ViewUtil.render(request, model, Path.Template.SELECTION);
    };

    public static String serveOtherPages(Request request, Response response, String displayString) {

        Map<String, Object> model = new HashMap<>();

        model.put("displayText", displayString);

        return ViewUtil.render(request, model, Path.Template.HOME);
    };
}
