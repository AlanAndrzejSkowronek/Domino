package Logic;

import java.util.ArrayList;
import java.util.Collections;
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

    public int getCardFromHand(int indexHand, int indexCard){
        return hand.get(indexHand).getCard()[indexCard];
    }
    public int getHandSize(){
        return (hand.size() - 1);
    }
    public void addToHand(DominoCard card){
        hand.add(card);
        hand.sort(DominoCard::compareTo);
        hand.sort(Collections.reverseOrder());
    }

    public void getMaxCard(){

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
        hand.get(index).printMinimalCard(canBePlayed);
    }
}
