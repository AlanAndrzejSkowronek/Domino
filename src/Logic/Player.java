package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;
    private List<DominoCard> hand;

    private Team team;

    public Player (String name, Team t){
        setName(name);
        hand = new ArrayList<>();
        team = t;
    }

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

    public Team getTeam(){
        return team;
    }

    public void setTeam(Team t){
        this.team = t;
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

        int place = ( pos ) ? (cardsGame.size() - 1) : 0 ;
        cardsGame.add(place, card);

        hand.remove(card);
    }

    public void showHand(boolean printMinimalCards){
        for (DominoCard dominoCard : hand) {
            if (printMinimalCards)
                dominoCard.printMinimalCard(false);
            else
                dominoCard.printCard();
        }
    }

    public void showOneCardFromHand(int index, boolean canBePlayed){
        hand.get(index).printMinimalCard(canBePlayed);
    }
}
