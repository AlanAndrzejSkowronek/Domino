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

    public int getFirstGamePos(){
        return cardsGame.get(0).getCard()[0];
    }

    public int getLastGamePos(){
        return cardsGame.get(cardsGame.size() - 1).getCard()[1];
    }

    public void addCardToGame(int idx, DominoCard card){
        cardsGame.add(idx, card);
    }

    public void addCardToGame(DominoCard card){
        cardsGame.add(card);
    }

    public void clearCardsInGame(){ cardsGame.clear(); }
}
