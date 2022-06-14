package Rules;

import Logic.DominoCard;
import Logic.Player;

import java.util.List;

public abstract class Rules {

    int max_points;
    public abstract int initCards(List<DominoCard> deck);
    public abstract int getMax_points();
    public abstract void givePointsToTeams(List<Player> players);
    public abstract int playerTotalPointsAtRound(Player p);
}
