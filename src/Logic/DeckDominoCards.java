package Logic;

import java.util.ArrayList;
import java.util.List;

public class DeckDominoCards {

    private List<DominoCard> deckCards;

    public DeckDominoCards(){
        deckCards = new ArrayList<>();
    }
    public List<DominoCard> getDeckCards() {
        return deckCards;
    }
}
