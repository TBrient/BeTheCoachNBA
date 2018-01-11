package util;

import lombok.Getter;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {
        @Getter public static final String HOME = "/game/";
        @Getter public static final String SELECTION = "/selection/";
        @Getter public static final String MAINSCREEN = "/main/";
        @Getter public static final String ROSTER = "/roster/";
    }

    public static class Template {
        public static final String HOME = "/velocity/game.vm";
        public static final String SELECTION = "/velocity/selectionScreen.vm";
        public static final String MAINSCREEN = "/velocity/mainScreen.vm";
        public static final String ROSTER = "/velocity/roster.vm";
    }

}
