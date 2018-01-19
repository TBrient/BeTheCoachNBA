import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by student on 12/19/17.
 */
public class Team {
    private ArrayList<Player> team = new ArrayList<>();
    private int win, loss;
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

    public ArrayList<Team> simMonth(ArrayList<Team> teams) {
        for (int i = 0; i < teams.size() / 2; i++) {
            teams.get(i).setLoss((int) (Math.random() * 12 - 1) + teams.get(i).getLoss());
            teams.get(i).setWin(12 + teams.get(i).getWin() - teams.get(i).getLoss());
        }
        for (int i = teams.size() / 2; i < teams.size(); i++) {
            teams.get(i).setLoss(teams.get(i - teams.size() / 2).getLoss());
            teams.get(i).setWin(teams.get(i - teams.size() / 2).getWin());
        }
        for (int i = 0; i < teams.size()-1; i++) {
            int mutate = (int) (Math.random() * 100);
            if(teams.get(i).getWin() > 2 && teams.get(i).getLoss() > 2) {
                if (mutate > 25 && mutate < 50) {
                    teams.get(i).setLoss(teams.get(i).getLoss() - 2);
                    teams.get(i).setWin(teams.get(i).getWin() + 2);
                    teams.get(i + 1).setLoss(teams.get(i).getLoss() + 2);
                    teams.get(i + 1).setWin(teams.get(i).getWin() - 2);
                }
                if (mutate > 0 && mutate < 25) {
                    teams.get(i).setLoss(teams.get(i).getLoss() + 2);
                    teams.get(i).setWin(teams.get(i).getWin() - 2);
                    teams.get(i + 1).setLoss(teams.get(i).getLoss() - 2);
                    teams.get(i + 1).setWin(teams.get(i).getWin() + 2);
                }
            }
        }
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getLoss() - o2.getLoss();
            }
        });

        return teams;
    }

    public String gameScore(Team team1) {
        int score = 0;
        int score1 = 0;
        String answer;
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
            score1 += team1.getTeam().get(i).getRating();
        }
        Random j = new Random();
        double rand = j.nextGaussian() * 20;
        double rand1 = j.nextGaussian() * 20;
        score += (int) (rand);
        score1 += (int) (rand1);
        if (rand > rand1) {
            answer = this.getName() + "defeats " + team1.getName() + score + "to " + score1;
        } else {
            answer = team1.getName() + "defeats " + this.getName() + score1 + "to " + score;
        }
        return answer;
    }

    public int getRating() {
        int rating = 0;
        for (int i = 0; i < team.size(); i++) {
            rating += team.get(i).getRating();
        }
        return rating;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }
}
