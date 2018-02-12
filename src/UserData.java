import java.util.ArrayList;

public class UserData {

    private ArrayList<Team> allTeams = new ArrayList<Team>();
    private Team userTeam;
    private String managerName;
    private ArrayList<Player> userReplacements = new ArrayList<>();
    private Boolean pickFive;
    private ArrayList <Player> randomReplace = new ArrayList<>();

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
}
