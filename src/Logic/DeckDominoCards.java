package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Rules.*;

public class DeckDominoCards implements printDeck {
    private List<DominoCard> deckCards;
    private Rules rules;
    private int numberOfCardsInDeck;
    public DeckDominoCards(Rules r){
        rules = r; // PRUEBAS
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

    public void printActualCards(){
        for (DominoCard deckCard : deckCards) {
            deckCard.printMinimalCard();
        }
    }

    public void giveCardsToPlayer(Player p, int numOfCards){
        int cardsGiven = 0;
        while (cardsGiven < numOfCards){
            p.getHand().add(deckCards.get(0));
            deckCards.remove(0);

            cardsGiven++;
        }
    }
}
