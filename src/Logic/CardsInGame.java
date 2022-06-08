package Logic;

import java.util.ArrayList;
import java.util.List;

public class CardsInGame implements printDeck {

    List<DominoCard> cardsGame;

    public CardsInGame(){
        cardsGame = new ArrayList<>();
    }

    public void printActualCards(){
        for (DominoCard card : cardsGame){
            card.printCard();
        }
    }

    public List<DominoCard> getCardsInGame(){
        return cardsGame;
    }
}
