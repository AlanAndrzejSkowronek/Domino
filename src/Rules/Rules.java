package Rules;

import Logic.DominoCard;
import Logic.Player;

import java.util.List;

public abstract class Rules {

    public abstract int initCards(List<DominoCard> deck);
    public abstract void firstMove(List<Player> listPlayers);
}
