package Logic;

import InOutUser.InOutUser;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private List<Player> team;
    private int teamID;
    private int points;

    public Team(int p){
        team = new ArrayList<>();
        points = p;
    }

    public void getPlayerOfTeam(int idx){
        team.get(idx);
    }

    public void addPlayer(Player pl){
        this.team.add(pl);
    }

    public int getTeamID(){
        return teamID;
    }

    public void setTeamID(int id){
        this.teamID = id;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int p){
        this.points = p;
    }

    public void addPoints(int pAdded){
        this.points += pAdded;
    }

    public static void create2Teams(List<Player> players){
        Team t1 = new Team(0);
        t1.setTeamID(1);
        Team t2 = new Team(0);
        t2.setTeamID(2);
        for (int i = 0; i < players.size(); i++) {
            if (i == 1 || i == 3){
                players.get(i).setTeam(t1);
                t1.addPlayer(players.get(i));
            } else {
                players.get(i).setTeam(t2);
                t2.addPlayer(players.get(i));
            }
        }
    }

    public static void createUniqueTeams(List<Player> players){
        for (int i = 0; i < players.size(); i++) {
            Team t = new Team(0);
            players.get(i).setTeam(t);
            t.addPlayer(players.get(i));
            t.setTeamID(i + 1);
        }
    }
}
