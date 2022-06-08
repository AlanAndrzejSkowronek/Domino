package Logic;

import java.util.ArrayList;
import java.util.List;

public class Team {

    List<Player> team;
    int points;

    public Team(int p){
        team = new ArrayList<>();
        points = p;
    }
}
