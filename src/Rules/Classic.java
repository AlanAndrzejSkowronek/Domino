package Rules;

import Logic.DominoCard;

import java.util.List;

public class Classic {

    public void initCards(List<DominoCard> deck){
        for (int i = 6; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                deck.add(new DominoCard(i, j));
            }
        }
    }
}
