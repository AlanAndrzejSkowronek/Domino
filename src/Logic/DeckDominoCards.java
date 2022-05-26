package Logic;

import java.util.ArrayList;
import java.util.List;
import Rules.Classic;

public class DeckDominoCards extends Classic{
    private DominoCard d;
    private List<DominoCard> deckCards;

    public DeckDominoCards(){
        deckCards = new ArrayList<>();
        initCards(deckCards);
    }
    public List<DominoCard> getDeckCards() {
        return deckCards;
    }
}
