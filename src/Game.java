import Logic.CardsInGame;
import Logic.DeckDominoCards;
import Logic.Player;

public class Game {

    private Player p = new Player("el moha");
    private DeckDominoCards deck = new DeckDominoCards();
    private CardsInGame cardsGame = new CardsInGame();


    //TODO
    public void givecards(){
        deck.giveCardsToPlayer(p, 7);
    }
    //TODO
    public void stealdeck(){
        deck.giveCardsToPlayer(p, 1);
        // comprobar si puede tirar ficha
    }
}
