import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by student on 12/19/17.
 */
public class Team {
    private ArrayList<Player> team = new ArrayList<>();
    private int win, loss, rating;
    private String name;
    private int tempLoss;

    public Team(ArrayList<Player> team, String name) {
        this.team = team;
        this.win = 0;
        this.loss = 0;
        this.name = name;
        rating = this.getRating();
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
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                //check if right order
                return o2.getRating() - o1.getRating();
            }
        });

        for (int i = 0; i < teams.size() / 2; i++) {
            if (i < 5) {
                tempLoss = teams.get(i).getLoss();
                teams.get(i).setLoss((int) (Math.random() * 5 ) + teams.get(i).getLoss());
                teams.get(i).setWin(12 - (teams.get(i).getLoss() - tempLoss) + teams.get(i).getWin());
            } else if (i < 10) {
                tempLoss = teams.get(i).getLoss();
                teams.get(i).setLoss((int) (Math.random() * 7 ) + teams.get(i).getLoss());
                teams.get(i).setWin(12 - (teams.get(i).getLoss() - tempLoss) + teams.get(i).getWin());
            } else if (i < teams.size() / 2) {
                tempLoss = teams.get(i).getLoss();
                teams.get(i).setLoss((int) (Math.random() * 9 ) + teams.get(i).getLoss());
                teams.get(i).setWin(12 - (teams.get(i).getLoss() - tempLoss) + teams.get(i).getWin());
            }
        }
        for (int i = teams.size()/2; i < teams.size(); i++) {
            teams.get(i).setLoss(teams.get(teams.size() - i-1).getWin());
            teams.get(i).setWin(teams.get(teams.size() - i-1).getLoss());
        }
        for (int i = 0; i < teams.size() - 1; i++) {
            int mutate = (int) (Math.random() * 100);
            if (teams.get(i).getWin() > 2 && teams.get(i).getLoss() > 2 && teams.get(i + 1).getWin() > 2 && teams.get(i + 1).getLoss() > 2) {
                if (mutate > 25 && mutate < 50) {
                    teams.get(i).setLoss(teams.get(i).getLoss() - 2);
                    teams.get(i).setWin(teams.get(i).getWin() + 2);
                    teams.get(i + 1).setLoss(teams.get(i + 1).getLoss() + 2);
                    teams.get(i + 1).setWin(teams.get(i + 1).getWin() - 2);
                }
                if (mutate > 0 && mutate < 25) {
                    teams.get(i).setLoss(teams.get(i).getLoss() + 2);
                    teams.get(i).setWin(teams.get(i).getWin() - 2);
                    teams.get(i + 1).setLoss(teams.get(i + 1).getLoss() - 2);
                    teams.get(i + 1).setWin(teams.get(i + 1).getWin() + 2);
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

    public ArrayList<Integer> gameScore(Team team1) {
        int score = 0;
        int score1 = 0;
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for (int i = 0; i < team.size(); i++) {
            score += team.get(i).getRating();
        }
        score = score/4;
        score1 += team1.getRating();
        Random j = new Random();
        double rand = j.nextGaussian() * 15;
        double rand1 = j.nextGaussian() * 15;
        score += (int) (rand);
        score1 += (int) (rand1);
        if(score1 == score){
            score1++;
        }
        answer.add(score);
        answer.add(score1);
        return answer;
    }

    public int getRating() {
        int rating = 0;
        for (int i = 0; i < team.size(); i++) {
            rating += team.get(i).getRating();
        }
        rating = rating / 5;
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
