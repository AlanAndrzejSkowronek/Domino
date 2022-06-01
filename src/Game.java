import InOutUser.InOutUser;
import Logic.CardsInGame;
import Logic.DeckDominoCards;
import Logic.Player;

public class Game {

    private Player p = new Player("el moha");
    private DeckDominoCards deck;
    private CardsInGame cardsGame = new CardsInGame();
    private InOutUser inp = new InOutUser();

    public void playGame(){
        inp.startGameMessage();
        deck = new DeckDominoCards(inp.pickRules());
    }


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
