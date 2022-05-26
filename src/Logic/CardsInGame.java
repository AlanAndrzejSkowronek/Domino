package Logic;

import java.util.ArrayList;
import java.util.List;

public class CardsInGame implements printDeck {

    private List<DominoCard> cardsGame;

    public CardsInGame(){
        cardsGame = new ArrayList<>();
    }
    public List<DominoCard> getCardsGame() {
        return cardsGame;
    }

    public void printActualCards(){
        for (DominoCard card : cardsGame){
            card.printCard(card);
        }
    }
}
