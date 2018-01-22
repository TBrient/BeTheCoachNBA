import spark.Request;
import spark.Response;
import util.Path;
import util.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by student on 12/19/17.
 */
public class ViewController {
    public static String serveSelectionPage(Request request, Response response, ArrayList<Team> teams) {

        Map<String, Object> model = new HashMap<>();

        ArrayList<ArrayList<Team>> TeamSplit = new ArrayList<ArrayList<Team>>();

        for (int i = 0; i < 5; i++) {
            ArrayList<Team> temp = new ArrayList<Team>();
            for (int j = i*6; j < i*6+6; j++) {
                temp.add(teams.get(j));
            }
            TeamSplit.add(temp);
        }


        model.put("teamSplit", TeamSplit);

        return ViewUtil.render(request, model, Path.Template.SELECTION);
    };
    public static String serveRoster(Request request, Response response, Team team, String manager){
        Map<String,Object> model = new HashMap<>();
        ArrayList<Player> roster = new ArrayList<>();
        roster = team.getTeam();

        model.put("managerName", manager);
        model.put("team", team);

        return ViewUtil.render(request, model, Path.Template.ROSTER);
    }

    public static String serveGamePlay(Request request, Response response, ArrayList<Team> teams){
        Map<String,Object> model = new HashMap<>();
        ArrayList<Player> roster = new ArrayList<>();

        ArrayList<Team> scoredTeams = teams.get(0).simMonth(teams);

        model.put("teams", scoredTeams);

        return ViewUtil.render(request, model, Path.Template.GAMEPLAY);
    }

    public static String serveOtherPages(Request request, Response response, String displayString) {

        Map<String, Object> model = new HashMap<>();

        model.put("displayText", displayString);

        return ViewUtil.render(request, model, Path.Template.HOME);
    };
}
