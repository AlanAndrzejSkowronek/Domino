package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    //TODO equipos y puntuacion generalizada.
    private String name;
    private List<DominoCard> hand;

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

    public DominoCard getMaxCard(){
        return hand.get(0);
    }

                                                                // FALSE = IZQUIERDA, TRUE = DERECHA
    public void addCardToGame(List<DominoCard> cardsGame, DominoCard card, boolean pos){
        if (pos)
            cardsGame.add((cardsGame.size() - 1), card);
        else
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
