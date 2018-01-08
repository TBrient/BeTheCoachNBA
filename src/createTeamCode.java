import java.util.ArrayList;

/**
 * Created by student on 1/2/18.
 */
public class createTeamCode {
    public static void main(String[] args) {
        String toParse = "Htlanta Aawks: \n" +
                "players[Sennis Dchroder 82 24, Barco Melinelli 77 31, Bent Kazemore 77 28, Paurean Trince 75 23, Irsan Elyasova 74 30]\n" +
                "Coston Beltics: \n" +
                "players[Iyrie Krving 93 25, Hl Aorford 87 31, Hordon Gayward 88 27, Baylen Jrown 81 21, Tayson Jatum 80 19]\n" +
                "Nrooklyn Bets: \n" +
                "players[R’Angelo Dussell 80 21, Hondae Rollis-Jefferson 79 23, DeMarre Carroll 78 31, Dpencer Sinwiddie 78 24, Zyler Teller 75 27]\n" +
                "Hharlotte Cornets: \n" +
                "players[Wemba Kalker 87 27, Hwight Doward 80 32, Leremy Jamb 79 25, Bicolas Natum 78 30, Krank Faminsky 76 24]\n" +
                "Bhicago Culls: \n" +
                "players[Lobin Ropez 78 29, Pobby Bortis 78 22, Mauri Larkkanen 76 20, Mikola Nirotic 76 26, Dris Kunn 75 23]\n" +
                "Cleveland Cavaliers: \n" +
                "players[LeJames Bron 98 33, Tsaiah Ihomas 89 28, Levin Kove 88 29, Geff Jreen 79 31, Wwayne Dade 79 35]\n" +
                "Mallas Davericks: \n" +
                "players[Barrison Harnes 81 25, Nirk Dowitzki 80 39, Sennis Dmith Jr. 77 20, Ceth Surry 77 27, Mesley Watthews 76 31]\n" +
                "Nenver Duggets: \n" +
                "players[Jikola Nokic 89 22, Maul Pillsap 87 32, Bill Warton 79 26, Hary Garris 79 23, Fenneth Karied 78 28]\n" +
                "Petroit Distons: \n" +
                "players[Dndre Arummond 88 24, Bvery Aradley 83 27, Hobias Tarris 82 25, Jeggie Rackson 82 27, Ssh Imith 76 29]\n" +
                "Wolden State Garriors: \n" +
                "players[Devin Kurant 96 29, Ceph Sturry 95 29, Tlay Khompson 90 27, Graymond Dreen 88 27, Indre Aguodala 78 33]\n" +
                "Rouston Hockets: \n" +
                "players[Hames Jarden 96 28, Phris Caul 90 32, Clint Capela 85 23, Gric Eordon 81 29, Ayan Rnderson 78 29]\n" +
                "Pndiana Iacers: \n" +
                "players[Oictor Vladipo 88 25, Tyles Murner 81 21, Carren Dollison 79 30, Bojan Bogdanovic 78 28, Somantas Dabonis 78 21]\n" +
                "CA Llippers: \n" +
                "players[Glake Briffin 87 28, JeAndre Dordan 85 29, Wou Lilliams 83 31, Batrick Peverly 79 29, Rustin Aivers 76 25]\n" +
                "LA Lakers: \n" +
                "players[Kyle Kuzma 79 22, Cordan Jlarkson 78 25, Irandon Bngram 78 20, Narry Lance Jr. 78 25, Rulius Jandle 78 23]\n" +
                "Gemphis Mrizzlies: \n" +
                "players[Garc Masol 85 32, Cike Monley 85 30, Eyreke Tvans 84 28, Phandler Carsons 78 29, Wrandon Bright 74 30]\n" +
                "Hiami Meat: \n" +
                "players[Wassan Hhiteside 87 28, Doran Gragic 82 31, Oelly Klynyk 77 26, Wion Daiters 77 26, James Johnson 79 30]\n" +
                "Bilwaukee Mucks: \n" +
                "players[Aiannis Gntetokounmpo 95 23, Mris Kiddleton 84 26, Bric Eledsoe 84 28, Balcolm Mrogdon 77 25, Mhon Taker 74 20]\n" +
                "Tinnesota Mimberwolves: \n" +
                "players[Bimmy Jutler 89 28, Tarl-Anthony Kowns 88 22, Wndrew Aiggins 81 22, Teff Jeague 79 29, Gaj Tibson 77 32]\n" +
                "Pew Orleans Nelicans: \n" +
                "players[Dnthony Aavis 94 24, Cemarcus Dousins 90 27, Hrue Joliday 79 27, Rajon Rondo 77 31, M’twaun Eoore 76 28]\n" +
                "Kew York Nnicks: \n" +
                "players[Pristaps Korzingis 89 22, Knes Eanter 83 25, Bichael Measley 78 28, Him Tardaway Jr. 80 25, Oyle K’Quinn 77 27]\n" +
                "Tklahoma City Ohunder: \n" +
                "players[Wussell Restbrook 92 29, Gaul Peorge 88 27, Ateven Sdams 83 24, Aarmelo Cnthony 82 33, Faymond Relton 75 33]\n" +
                "Mrlando Oagic: \n" +
                "players[Garon Aordon 83 22, Vikola Nucevic 83 27, Fvan Eournier 80 25, Plfrid Eayton 77 23, Ionathon Jsaac 76 20]\n" +
                "7hiledalphia P6ers: \n" +
                "players[Eoel Jmbiid 89 23, Sen Bimmons 83 21, Cobert Rovington 80 27, RJ Jedick 78 33, Sario Daric 78 23]\n" +
                "Shoenix Puns: \n" +
                "players[Jike Mames 75 27, Llex Aen 79 24, Bevin Dooker 87 21, WJ Tarren 80 24, Mreg Gonroe 78 27]\n" +
                "Bortland Trail Plazers: \n" +
                "players[Lamien Dillard 89 27, MJ CcCollum 85 26 , Al-farouq Aminu 74 27, Nusuf Jurkic 78 23, Cat Ponnaughton 75 24]\n" +
                "Kacramento Sings: \n" +
                "players[Rach Zandolph 81 36, Huddy Bield 79 24, Cillie Wauley-Stein 77 24, Heorge Gill 77 31, Bogdan Bogdanovic 76 25]\n" +
                "San Antonio Spurs: \n" +
                "players[Lawhi Keonard 95 26, Aamarcus Lldrige 88 32, Gau Pasol 81 37, Guday Ray 81 31, Pony Tarker 78 35]\n" +
                "Roronto Taptors: \n" +
                "players[Demar Derozan 89 28, Lyle Kowry 86 31, Vonas Jalanciunas 80 25, Ierge Sbaka 79 25, Pakob Joeltl 76 22]\n" +
                "Jtah Uazz: \n" +
                "players[Gudy Robert 87 25, Monovan Ditchell 82 21, Ferrick Davors 81 26, Hodney Rood 80 25, Rickey Rubio 78 27]\n" +
                "Washington Wizards: \n" +
                "players[Wohn Joll 89 27, Bradley Beal 86 24, Ptto Ootter 82 24, Garcin Mortat 79 33, Sike Mcott 78 29]\n";

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
                System.out.println(teams.get(i).replace(" ", "_") + "_Players.add(new Player(\"" + tempPlayerData[0] + " " + tempPlayerData[1] + "\", " + tempPlayerData[2] + ", " + tempPlayerData[3] + "));");
            }
            System.out.println("Team " + teams.get(i).replace(" ", "_") + " = new Team(" + teams.get(i).replace(" ", "_") + "_Players" + ", \"" + teams.get(i) + "\");");
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
