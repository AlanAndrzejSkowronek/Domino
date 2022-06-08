package Logic;

import java.util.ArrayList;
import java.util.List;

public class CardsInGame implements printDeck {

    private List<DominoCard> cardsGame;

    public CardsInGame(){
        cardsGame = new ArrayList<>();
    }

    public void printActualCards(){
        System.out.println(" - - - - - - - - - - - - Cards Played - - - - - - - - - - - - ");
        for (DominoCard card : cardsGame){
            card.printCard();
        }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
    }

    public List<DominoCard> getCardsInGame(){
        return cardsGame;
    }
}
