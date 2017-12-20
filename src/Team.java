import java.util.ArrayList;

/**
 * Created by student on 12/19/17.
 */
public class Team {
    private ArrayList<Player> team = new ArrayList<>();

    public Team(ArrayList<Player> team) {
        this.team = team;
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Player> team) {
        this.team = team;
    }

    public int[] setRecord(){
        int score = 0;
        int ans[] = new int[2];
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
        }
        ans[0] = 82 * ( 1 - (score / 40) * 2);
        ans[1] = 82 - ans[0];
        return ans;
    }
    public void simRecords(ArrayList<Team> teams){
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setRecord();
        }
    }
}
