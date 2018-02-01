import org.jsoup.Jsoup;
import spark.Session;
import util.Filters;
import util.Path;
import util.ViewUtil;

import javax.swing.text.View;

import java.util.ArrayList;
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
            return ViewController.serveRoster(req, res, currentUserData.getUserTeam(), currentUserData.getManagerName());
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
            System.out.println("caleld");
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
            System.out.println(req.cookie("JSESSIONID"));
            System.out.println(currentUserData.getManagerName());
            return ViewController.serveRoster(req, res, currentUserData.getUserTeam(), currentUserData.getManagerName());
        });

        post(Path.Web.ROSTER,       (req, res) -> {
            String[] selectedPlayers = req.queryParamsValues("playerSelection");
            for (int i = 0; i < selectedPlayers.length; i++) {
                System.out.println(selectedPlayers[i]);
            }
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            res.redirect(Path.Web.GAMEPLAY);
            return null;
        });

        get(Path.Web.GAMEPLAY,       (req, res) -> {
            UserData currentUserData = userData.get(req.cookie("JSESSIONID"));
            System.out.println(req.cookie("JSESSIONID"));
            System.out.println(currentUserData.getManagerName());
            return ViewController.serveGamePlay(req, res, currentUserData);
        });

//        redirect.get("*", Path.Web.SELECTION);

//        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
    }

}
