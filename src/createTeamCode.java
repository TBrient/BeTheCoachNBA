import java.util.ArrayList;

/**
 * Created by student on 1/2/18.
 */
public class createTeamCode {
    public static void main(String[] args) {
        String toParse = "Atlanta Hawks: \n" +
                "players[Sennis Dchroder 82 24, Barco Melinelli 77 31, Bent Kazemore 77 28, Paurean Trince 75 23, Irsan Elyasova 74 30]\n";
//                "Boston Celtics: \n" +
//                "players[Iyrie Krving 93, Hl Aorford 87, Hordon Gayward 88, Baylen Jrown 81, Tayson Jatum 80]\n" +
//                "Brooklyn Nets: \n" +
//                "players[R’Angelo Dussell 80, Hondae Rollis-Jefferson 79, DeMarre Carroll 78, Dpencer Sinwiddie 78, Zyler Teller 75]\n" +
//                "Charlotte Hornets: \n" +
//                "players[Kemba Walker 87, Dwight Howard 80, Jeremy Lamb 79, Nicolas Batum 78, Frank Kaminsky 76]\n";

        String[] splitUp = toParse.split("\n");
        System.out.println(splitUp.length);

        ArrayList<String> teams = new ArrayList<>();
        ArrayList<String> players = new ArrayList<>();

        for (int i = 0; i < splitUp.length; i++) {
            if (i%2 == 0) {
                teams.add(splitUp[i].replace(":", "").trim());
            } else {
                players.add(splitUp[i].replace("players[", "").replace("]", "").trim());
            }
        }
//
//        printArrayList(teams);
//        printArrayList(players);

        System.out.println("ArrayList<Team> allTeams = new ArrayList<Team>()");

        for (int i = 0; i < players.size(); i++) {
            String[] tempPlayers = players.get(i).split(", ");
            System.out.println("ArrayList<Player> " + teams.get(i).replace(" ", "_") + "_Players = new ArrayList<Player>()");
            for (int j = 0; j < tempPlayers.length; j++) {
                String[] tempPlayerData = tempPlayers[j].split(" ");
                System.out.println(teams.get(i).replace(" ", "_") + "_Players.add(new Player(" + tempPlayerData[0] + " " + tempPlayerData[1] + ", " + tempPlayerData[2] + ", " + tempPlayerData[3] + "));");
            }
            System.out.println("Team " + teams.get(i).replace(" ", "_") + " = new Team(" + teams.get(i).replace(" ", "_") + "_Players" + ", " + teams.get(i) + ");");
            System.out.println("allTeams.add(" + teams.get(i).replace(" ", "_") + ");");
        }

    }

    public static void printArrayList(ArrayList<String> stringArr) {
        System.out.print("[");
        for (int i = 0; i < stringArr.size(); i++) {
            System.out.print("{" + stringArr.get(i) + "}, ");
        }
        System.out.println("]");
    }
}
