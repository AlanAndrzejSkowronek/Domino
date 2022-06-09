package Rules;

import Logic.CardsInGame;
import Logic.DominoCard;
import Logic.Player;

import java.util.List;

public class Classic extends Rules {

    int max_points = 120;
    public int initCards(List<DominoCard> deck){
        int numberOfCardsCreated = 0;
        for (int i = 6; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                deck.add(new DominoCard(i, j));
                numberOfCardsCreated++;
            }
        }
        return numberOfCardsCreated;
    }

    @Override
    public int getMax_points() {
        return max_points;
    }
}
