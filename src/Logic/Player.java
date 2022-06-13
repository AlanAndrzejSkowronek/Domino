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

    public Team getTeam(){
        return team;
    }

    public void setTeam(Team t){
        this.team = t;
    }

    public int getCardFromHand(int indexHand, int indexCard){
        return hand.get(indexHand).getCard()[indexCard];
    }
    public DominoCard getCardFromHand(int indexHand){return hand.get(indexHand);}
    public boolean isPlayerHandEmpty(){
        return hand.isEmpty();
    }
    public int getHandSize(){
        return hand.size();
    }
    public void addToHand(DominoCard card){
        hand.add(card);
        hand.sort(DominoCard::compareTo);
        hand.sort(Collections.reverseOrder());
    }

    public DominoCard getMaxCard(){
        return hand.get(0);
    }

    public void addFirstCard(List<DominoCard> cardsGame, DominoCard card){
        cardsGame.add(0, card);
        hand.remove(card);
    }

    public void addCardToExistingGame(List<DominoCard> cardsGame, DominoCard card){
        int firstGamePos = cardsGame.get(0).getCard()[0];
        int lastGamePos = cardsGame.get(cardsGame.size() - 1).getCard()[1];

        if (verifyLeftPlayable(hand.indexOf(card), firstGamePos))
            cardsGame.add(0, card);
        else if (verifyRightPlayable(hand.indexOf(card), lastGamePos))
            cardsGame.add(card);

        if (!verifyPlayableCard(cardsGame, hand.indexOf(card))){
            System.out.println("Esta carta no es jugable!");
            return;
        }

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

    public boolean verifyPlayableCards(List<DominoCard> cardsGame){
        for (int i = 0; i < getHandSize(); i++){
            if(verifyPlayableCard(cardsGame, i))
                return true;
        }
        return false;
    }

    public boolean verifyPlayableCard(List<DominoCard> cardsGame, int indexHand){
        int firstGamePos = cardsGame.get(0).getCard()[0];
        int lastGamePos = cardsGame.get(cardsGame.size() - 1).getCard()[1];

        return verifyLeftPlayable(indexHand, firstGamePos)
                || verifyRightPlayable(indexHand, lastGamePos);
    }

    public boolean verifyLeftPlayable(int indexHand, int firstGamePos){
        return getCardFromHand(indexHand, 0) == firstGamePos
                || getCardFromHand(indexHand, 1) == firstGamePos;
    }

    public boolean verifyRightPlayable(int indexHand, int lastGamePos){
        return getCardFromHand(indexHand, 0) == lastGamePos
                || getCardFromHand(indexHand, 1) == lastGamePos;
    }

    public int showPlayableCards(List<DominoCard> cardsGame){
        int playableCards = 0;

        for (int i = 0; i < getHandSize(); i++){
            showOneCardFromHand(i, verifyPlayableCard(cardsGame, i));
            if (verifyPlayableCard(cardsGame, i))
                playableCards++;
        }
        return playableCards;
    }
}
