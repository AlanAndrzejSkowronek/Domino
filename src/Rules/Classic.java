package Rules;

import Logic.DominoCard;

import java.util.List;

public class Classic {

    public void initCards(List<DominoCard> deck){
        int x = 1;

        for (int i = 6; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                deck.add(new DominoCard(i, j));
                System.out.println("Card " + x + " : " + deck.get(deck.size() - 1).getCard()[0] + " " + deck.get(deck.size() - 1).getCard()[1]);
                System.out.println(" - - - - - - - - - - - - - - - -");
                x++;
            }
        }
    }
}
