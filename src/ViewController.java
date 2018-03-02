import spark.Request;
import spark.Response;
import util.Path;
import util.ViewUtil;

import java.util.*;


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
    //public static Boolean pickFive = false;
    public static String serveRoster(Request request, Response response, UserData userData){
        Map<String,Object> model = new HashMap<>();

        ArrayList<Player> replacements = (ArrayList<Player>)userData.getUserReplacements().clone();

       ArrayList<Player> replacementsRandom = new ArrayList<>();
        //if (pickFive) {
        if (userData.getRandomReplace().size() == 0) {


            for (int i = 0; i < 5; i++) {
                int rand = (int) (Math.random() * replacements.size());
                replacementsRandom.add(replacements.get(rand));
                replacements.remove(rand);
            }
            userData.setRandomReplace(replacementsRandom);
        }    //}




        model.put("managerName", userData.getManagerName());
        model.put("team", userData.getUserTeam());
        model.put("replacements", userData.getRandomReplace());
        model.put("pickFive", userData.getPickFive());

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
        Team fakeTeam = new Team(new ArrayList<Player>(), null);
        fakeScore.add(null);
        fakeScore.add(null);
        model.put("game1Score", fakeScore);
        model.put("game2Score", fakeScore);
        model.put("game3Score", fakeScore);
        model.put("winner1", fakeTeam);
        model.put("winner2", fakeTeam);
        model.put("winner3", fakeTeam);





        return ViewUtil.render(request, model, Path.Template.BRACKET);
    };

    public static String serveBracketPage(Request request, Response response, ArrayList<Team> teams, ArrayList<Integer> game1Score, ArrayList<Integer> game2Score, ArrayList<Integer> game3Score, Team winner1, Team winner2, Team winner3) {

        Map<String, Object> model = new HashMap<>();
        model.put("teams", teams);
        model.put("game1Score", game1Score);
        model.put("game2Score", game2Score);
        model.put("game3Score", game3Score);
        model.put("winner1", winner1);
        model.put("winner2", winner2);
        model.put("winner3", winner3);









        return ViewUtil.render(request, model, Path.Template.BRACKET);
    };
}
