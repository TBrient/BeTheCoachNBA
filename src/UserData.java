import java.util.ArrayList;

public class UserData {

    private ArrayList<Team> allTeams = new ArrayList<Team>();
    private Team userTeam;
    private String managerName;
    private ArrayList<Player> userReplacements = new ArrayList<>();
    private Boolean pickFive;
    private ArrayList <Player> randomReplace = new ArrayList<>();
    private int[] record = new int[2]; //Wins,Losses
    private int[] carrerRecord = new int[2]; //wins, losses
    private Boolean tourneyPlay = false;

    public Boolean getTourneyPlay() {
        return tourneyPlay;
    }

    public void setTourneyPlay(Boolean tourneyPlay) {
        this.tourneyPlay = tourneyPlay;
    }

    public ArrayList<Player> getRandomReplace() {
        return randomReplace;
    }

    public void setRandomReplace(ArrayList<Player> randomReplace) {
        this.randomReplace = randomReplace;
    }

    public Boolean getPickFive() {
        return pickFive;
    }

    public void setPickFive(Boolean pickFive) {
        this.pickFive = pickFive;
    }

    public ArrayList<Player> getUserReplacements() {
        return userReplacements;
    }

    public void setUserReplacements(ArrayList<Player> userReplacements) {
        this.userReplacements = userReplacements;
    }

    public UserData() {
        allTeams = TeamHelper.getTeamArrayList();
        userReplacements = TeamHelper.getReplacements();
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }

    public Team getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(Team userTeam) {
        this.userTeam = userTeam;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void endYear(){
        record[0] = userTeam.getWin();
        record[1] = userTeam.getLoss();
        carrerRecord[0] += record[0];
        carrerRecord[1] += record[1];
        record[0] = 0;
        record[1] = 0;
        userTeam.setLoss(0);
        userTeam.setWin(0);
        for (int i = 0; i < userTeam.getTeam().size() ; i++) {
            userTeam.getTeam().get(i).updateRating();
        }
        for (int i = 0; i < userReplacements.size(); i++) {
            userReplacements.get(i).updateRating();
        }
        for (int i = 0; i < getAllTeams().size(); i++) {
            getAllTeams().get(i).setWin(0);
            getAllTeams().get(i).setLoss(0);
        }
        //more
        pickFive = false;
        tourneyPlay = false;
        randomReplace.clear();

    }
}
