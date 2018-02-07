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

        ArrayList<Player> replacements = TeamHelper.getReplacements();

        ArrayList<Player> replacementsRandom = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int rand = (int)(Math.random()*replacements.size());
            replacementsRandom.add(replacements.get(rand));
            replacements.remove(rand);
        }

        model.put("managerName", manager);
        model.put("team", team);
        model.put("replacements", replacementsRandom);

        return ViewUtil.render(request, model, Path.Template.ROSTER);
    }

    public static String serveGamePlay(Request request, Response response, UserData userData){
        Map<String,Object> model = new HashMap<>();

        ArrayList<Team> scoredTeams = userData.getAllTeams().get(0).simMonth(userData.getAllTeams());
        userData.setAllTeams(scoredTeams);

        System.out.println(userData.getManagerName());

        model.put("teams", userData.getAllTeams());
        model.put("myTeamName", userData.getUserTeam().getName());

        return ViewUtil.render(request, model, Path.Template.GAMEPLAY);
    }

    public static String serveOtherPages(Request request, Response response, String displayString) {

        Map<String, Object> model = new HashMap<>();

        model.put("displayText", displayString);

        return ViewUtil.render(request, model, Path.Template.HOME);
    };

    public static String serveBracketPage(Request request, Response response, ArrayList<Team> teams) {

        Map<String, Object> model = new HashMap<>();
        model.put("teams", teams);
        ArrayList<Integer> fakeScore = new ArrayList<Integer>();
        fakeScore.add(null);
        fakeScore.add(null);
        model.put("game1Score", fakeScore);
        model.put("game2Score", fakeScore);


        return ViewUtil.render(request, model, Path.Template.BRACKET);
    };

    public static String serveBracketPage(Request request, Response response, ArrayList<Team> teams, ArrayList<Integer> game1Score, ArrayList<Integer> game2Score) {

        Map<String, Object> model = new HashMap<>();
        model.put("teams", teams);
        model.put("game1Score", game1Score);
        model.put("game2Score", game2Score);



        return ViewUtil.render(request, model, Path.Template.BRACKET);
    };
}
