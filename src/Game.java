import InOutUser.InOutUser;
import Logic.CardsInGame;
import Logic.DeckDominoCards;
import Logic.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player p;
    private DeckDominoCards deck;
    private CardsInGame cardsGame = new CardsInGame();
    private InOutUser inp = new InOutUser();
    private List<Player> listOfPlayers = new ArrayList<>();
    private int numberOfPlayers;

    public void playGame(){
        inp.startGameMessage();
        deck = new DeckDominoCards(inp.pickRules());

        numberOfPlayers = inp.pickNumberOfPlayers();
        inp.createPlayerObjects(numberOfPlayers, listOfPlayers);

        for (Player playerInGame : listOfPlayers) {
            giveCards(playerInGame);
            System.out.println("------------------- " + playerInGame.getName() + " hand ---------------------------------------------");
            playerInGame.showHand();
            System.out.println();
        }
        System.out.println("------------------- deck ---------------------------------------------");
        deck.printActualCards();
    }

    //TODO Generalizarlo para las normas, no todas reparten 7 fichas
    public void giveCards(Player p){
        deck.giveCardsToPlayer(p, 7);
    }
    //TODO
    public void stealDeck(){
        deck.giveCardsToPlayer(p, 1);
        // comprobar si puede tirar esta ficha robada
    }

    // metodo de si puede tirar ficha
}
