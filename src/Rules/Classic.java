package Rules;

import Logic.DominoCard;

import java.util.List;

public class Classic extends Rules {

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
}
