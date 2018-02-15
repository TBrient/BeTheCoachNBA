import org.jsoup.Jsoup;
import spark.Session;
import util.Filters;
import util.Path;
import util.ViewUtil;

import javax.swing.text.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application implements spark.servlet.SparkApplication{

    private static Map<String, UserData> userData = new HashMap<String, UserData>();



    @Override
    public void init() {

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);
//
        get(Path.Web.SELECTION,       (req, res) -> {
            userData.remove("JSESSIONID");
            userData.put("JSESSIONID", new UserData());
            return ViewController.serveSelectionPage(req, res, userData.get("JSESSIONID").getAllTeams());
        });

        post(Path.Web.SELECTION,       (req, res) -> {
            String myTeamName = req.queryParams("teamSelection");
            UserData currentUserData = userData.get("JSESSIONID");
            ArrayList<Team> allTeams = currentUserData.getAllTeams();
            for (int i = 0; i < allTeams.size(); i++) {
                if(myTeamName.equals(allTeams.get(i).getName())) {
                    currentUserData.setUserTeam(allTeams.get(i));
                }
            }
            currentUserData.setManagerName(req.queryParams("managerName"));
            res.redirect(Path.Web.ROSTER);
            return null;
        });

        get(Path.Web.ROSTER,       (req, res) -> {
            UserData currentUserData = userData.get("JSESSIONID");
            return ViewController.serveRoster(req, res, currentUserData);
        });

        get(Path.Web.GAMEPLAY,       (req, res) -> {
            UserData currentUserData = userData.get("JSESSIONID");
            return ViewController.serveGamePlay(req, res, currentUserData);
        });

        redirect.get("*", Path.Web.SELECTION);

//        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
    }

    public static void main(String[] args) {

        // Configure Spark
        port(4567);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);




//
        get(Path.Web.SELECTION,       (req, res) -> {
            Session oldSession = req.session(false);
            if (oldSession!=null && !oldSession.isNew()) {
                oldSession.invalidate();
            }
            Session session = req.session(true); // create the session
            return ViewController.serveSelectionPage(req, res, TeamHelper.getTeamArrayList());
        });

        post(Path.Web.SELECTION,       (req, res) -> {
            userData.remove(req.cookie("JSESSIONID"));
            userData.put(req.cookie("JSESSIONID"), new UserData());
            String myTeamName = req.queryParams("teamSelection");
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            ArrayList<Team> allTeams = currentUserData.getAllTeams();
            for (int i = 0; i < allTeams.size(); i++) {
                if(myTeamName.equals(allTeams.get(i).getName())) {
                    currentUserData.setUserTeam(allTeams.get(i));
                }
            }
            currentUserData.setManagerName(req.queryParams("managerName"));
            res.redirect(Path.Web.ROSTER);
            return null;
        });

        get(Path.Web.ROSTER,       (req, res) -> {
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            if (currentUserData != null) {
                return ViewController.serveRoster(req, res, currentUserData);
            } else {
                res.redirect(Path.Web.SELECTION);
                return null;
            }
        });

        post(Path.Web.ROSTER,       (req, res) -> {
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            String[] results = req.queryParamsValues("playerSelection");
            if(results != null && results.length == 5) {
                ArrayList<Player> temp = new ArrayList<>();
                for (int i = 0; i < results.length; i++) {
                    for (int j = 0; j < currentUserData.getUserTeam().getTeam().size(); j++) {
                        if (results[i].equals(currentUserData.getUserTeam().getTeam().get(j).getName())) {
                            temp.add(currentUserData.getUserTeam().getTeam().get(j));
                        }

                    }
                    for (int j = 0; j < currentUserData.getUserReplacements().size(); j++) {
                        if (results[i].equals(currentUserData.getUserReplacements().get(j).getName())) {
                            temp.add(currentUserData.getUserReplacements().get(j));
                            currentUserData.getUserReplacements().remove(j);
                        }
                    }

                }
                currentUserData.getUserTeam().setTeam(temp);
                res.redirect(Path.Web.GAMEPLAY);
            }
            else{
                currentUserData.setPickFive(true);
                res.redirect(Path.Web.ROSTER);
            }
            return null;
        });

        get(Path.Web.GAMEPLAY,       (req, res) -> {
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            if (currentUserData != null) {
                return ViewController.serveGamePlay(req, res, currentUserData);
            } else {
                res.redirect(Path.Web.SELECTION);
                return null;
            }
        });

        post(Path.Web.GAMEPLAY,       (req, res) -> {
            res.redirect(Path.Web.BRACKET);
            return null;
        });

        get(Path.Web.BRACKET,       (req, res) -> {
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            if (currentUserData != null) {
                return ViewController.serveBracketPage(req,res, currentUserData.getAllTeams());
            } else {
                res.redirect(Path.Web.SELECTION);
                return null;
            }
        });
        post(Path.Web.BRACKET,       (req, res) -> {


            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            if(!currentUserData.getTourneyPlay()) {
                ArrayList<Team> team = currentUserData.getAllTeams();
                ArrayList<Integer> gameScore = team.get(0).gameScore(team.get(3));
                ArrayList<Integer> gameScore2 = team.get(1).gameScore(team.get(2));
                Team winner1;
                Team winner2;
                Team winner3;
                if (gameScore.get(0) > gameScore.get(1))
                    winner1 = team.get(0);
                else
                    winner1 = team.get(3);

                if (gameScore2.get(0) > gameScore2.get(1))
                    winner2 = team.get(1);
                else
                    winner2 = team.get(2);

                ArrayList<Integer> gameScore3 = winner1.gameScore(winner2);
                if (gameScore3.get(0) > gameScore3.get(1))
                    winner3 = winner1;
                else
                    winner3 = winner2;

                currentUserData.setTourneyPlay(true);


                return ViewController.serveBracketPage(req, res, currentUserData.getAllTeams(), gameScore, gameScore2, gameScore3, winner1, winner2, winner3);

            }else{
                res.redirect(Path.Web.ROSTER);
                currentUserData.endYear();
                return null;
            }
        });

//        redirect.get("*", Path.Web.SELECTION);

//        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
    }

}
