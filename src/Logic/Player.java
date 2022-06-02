package Logic;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<DominoCard> hand;
    private CardsInGame cardsGame;
    private DominoCard card;

    public Player (String name){
        setName(name);
        hand = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<DominoCard> getHand(){
        return hand;
    }

    //TODO: TIRAR FICHA
    public void addCardToGame(List<DominoCard> cardsGame, DominoCard card){
            cardsGame.add(card);
            cardsGame.add(0, card);

        hand.remove(card);
    }

    public void showHand(){
        for (DominoCard dominoCard : hand) {
            dominoCard.printMinimalCard(false);
        }
    }

    public void showOneCardFromHand(int index, boolean canBePlayed){
        getHand().get(index).printMinimalCard(canBePlayed);
    }
}
