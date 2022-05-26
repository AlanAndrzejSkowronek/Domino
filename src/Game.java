import Logic.CardsInGame;
import Logic.DeckDominoCards;
import Logic.Player;

public class Game {

    private Player p = new Player("el moha");
    private DeckDominoCards deck = new DeckDominoCards();
    private CardsInGame cardsGame = new CardsInGame();


    //TODO
    public void initcartas(){
        deck.giveCardsToPlayer(p, 7);
    }
    public void robarpozo(){
        deck.giveCardsToPlayer(p, 1);
        // comprobar si puede tirar ficha
    }
}
