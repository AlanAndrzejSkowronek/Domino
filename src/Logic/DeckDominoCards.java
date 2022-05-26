package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Rules.Classic;

public class DeckDominoCards extends Classic implements printDeck{
    private List<DominoCard> deckCards;

    public DeckDominoCards(){
        deckCards = new ArrayList<>();
        initCards(deckCards);
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
