package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private String name;
    private List<DominoCard> hand;

    private Team team;
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

    public int getTeamID(){
        return team.getTeamID();
    }

    public int getPoints(){ return team.getPoints(); }

    public void addPointsToTeam(int pointsAdded){ team.addPoints(pointsAdded); }

    public void setTeam(Team t){
        this.team = t;
    }

    public int getCardFromHand(int indexHand, int indexCard){
        return hand.get(indexHand).getCard()[indexCard];
    }
    public DominoCard getCardFromHand(int indexHand){return hand.get(indexHand);}
    public int getIndexOfCard(DominoCard card){ return hand.indexOf(card); }
    public boolean isPlayerHandEmpty(){
        return hand.isEmpty();
    }
    public int getHandSize(){
        return hand.size();
    }
    public void removeCardFromHand(DominoCard card){ hand.remove(card); }
    public void addToHand(DominoCard card){
        hand.add(card);
        hand.sort(DominoCard::compareTo);
        hand.sort(Collections.reverseOrder());
    }

    public void clearHand(){ hand.clear(); }

    public DominoCard getMaxCard(){
        return hand.get(0);
    }

    public DominoCard returnFirstCard(){
        DominoCard card = getMaxCard();
        hand.remove(card);
        return card;
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
