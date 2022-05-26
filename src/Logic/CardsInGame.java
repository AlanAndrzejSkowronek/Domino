package Logic;

import java.util.ArrayList;
import java.util.List;

public class CardsInGame {

    private List<DominoCard> cardsGame;

    public CardsInGame(){
        cardsGame = new ArrayList<>();
    }
    public List<DominoCard> getCardsGame() {
        return cardsGame;
    }
}
