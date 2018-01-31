import java.util.ArrayList;

public class UserData {

    private ArrayList<Team> allTeams = new ArrayList<Team>();
    private Team userTeam;
    private String managerName;

    public UserData() {
        allTeams = TeamHelper.getTeamArrayList();
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
