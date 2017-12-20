import java.util.ArrayList;

/**
 * Created by student on 12/19/17.
 */
public class Team {
    private ArrayList<Player> team = new ArrayList<>();
    private int[] record;

    public Team(ArrayList<Player> team) {
        this.team = team;
        record = new int[2];
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Player> team) {
        this.team = team;
    }

    public int[] setRecord(){
        int score = 0;
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
        }
        record[0] += (82 * ( 1 - (score / 40) * 2))/8;
        record[1] = 82/8 - record[0];
        return record;
    }
    public void simRecords(ArrayList<Team> teams){
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setRecord();
        }
    }

}
