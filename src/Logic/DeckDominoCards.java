package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Rules.*;

public class DeckDominoCards implements printDeck{
    private List<DominoCard> deckCards;
    private Rules r;
    public DeckDominoCards(){
        r = new Classic(); // PRUEBAS
        deckCards = new ArrayList<>();
        r.initCards(deckCards);
        // r.nada();
        randomizeDeck(deckCards);
    }

    private void randomizeDeck(List<DominoCard> deck){
        Collections.shuffle(deck);
    }

    public void printActualCards(){
        for (DominoCard deckCard : deckCards) {
            deckCard.printCard();
        }
    }

    public void giveCardsToPlayer(Player p, int numOfCards){
        int cardsGiven = 0;
        while (cardsGiven <= numOfCards){
            p.getHand().add(deckCards.get(0));
            deckCards.remove(0);

            cardsGiven++;
        }
    }
}
