package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Rules.*;

public class DeckDominoCards implements printDeck {
    private List<DominoCard> deckCards;
    private int numberOfCardsInDeck;
    public DeckDominoCards(Rules r){
        deckCards = new ArrayList<>();
        numberOfCardsInDeck = r.initCards(deckCards);
        randomizeDeck(deckCards);
    }

    public int getNumberOfCardsInDeck(){
        return this.numberOfCardsInDeck;
    }
    private void randomizeDeck(List<DominoCard> deck){
        Collections.shuffle(deck);
    }

    public boolean isEmpty(){
        return deckCards.isEmpty();
    }

    public void printActualCards(){
        for (DominoCard deckCard : deckCards) {
            deckCard.printMinimalCard(false);
        }
    }

    public void giveCardsToPlayer(Player p, int numOfCards){
        int cardsGiven = 0;
        while (cardsGiven < numOfCards){
            p.addToHand(deckCards.get(0));
            deckCards.remove(0);

            cardsGiven++;
        }
    }
}
