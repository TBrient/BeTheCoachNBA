import java.util.ArrayList;
import java.util.Random;

/**
 * Created by student on 12/19/17.
 */
public class Team {
    private ArrayList<Player> team = new ArrayList<>();
    private int win,loss;
    private String name;

    public Team(ArrayList<Player> team, String name) {
        this.team = team;
        this.win = 0;
        this.loss = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Player> team) {
        this.team = team;
    }

    public void setRecord(){
        int score = 0;
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
        }
        win += (82 * ( 1 - (score / 40) * 2))/8;
        loss = 82/8 - win;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
    }

    public void simRecords(ArrayList<Team> teams){
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setRecord();
        }
    }
    public String gameScore(Team team1){
        int score = 0;
        int score1 = 0;
        String answer;
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
            score1 += team1.getTeam().get(i).getRating();
        }
        Random j = new Random();
        double rand = j.nextGaussian()*20;
        double rand1 = j.nextGaussian()*20;
        score += (int)(rand);
        score1 += (int)(rand1);
        if(rand > rand1) {
            answer = this.getName() + "defeats " +team1.getName() + score + "to " + score1;
        }
        else{
            answer = team1.getName() + "defeats " +this.getName() + score1 + "to " + score;
        }
             return answer;
    }

}
