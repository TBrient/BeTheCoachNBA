import org.jsoup.Jsoup;
import util.Filters;
import util.Path;
import util.ViewUtil;

import javax.swing.text.View;

import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application implements spark.servlet.SparkApplication{



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
//        get(Path.Web.SELECTION,       (req, res) -> {
//            return ViewController.serveSelectionPage(req, res);
//        });

        redirect.get("*", Path.Web.HOME);

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
//        get(Path.Web.HOME,       (req, res) -> {
//            return ViewController.serveHomePage(req, res);
//        });

        redirect.get("*", Path.Web.HOME);

//        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
    }

}
