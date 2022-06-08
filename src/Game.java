import InOutUser.InOutUser;
import Logic.CardsInGame;
import Logic.DeckDominoCards;
import Logic.DominoCard;
import Logic.Player;
import Rules.Rules;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player p;
    private DeckDominoCards deck;
    private CardsInGame cardsGame = new CardsInGame();
    private Rules r;
    private InOutUser inp = new InOutUser();
    private List<Player> listOfPlayers = new ArrayList<>();
    private int numberOfPlayers;

    public void playGame(){
        inp.startGameMessage();
        r = inp.pickRules();
        deck = new DeckDominoCards(r);

        initPlayersAndGiveCards(listOfPlayers);
        firstMove(listOfPlayers, cardsGame.getCardsInGame());

    }

    //TODO Generalizarlo para las normas, no todas reparten 7 fichas
    public void giveCards(Player p){
        deck.giveCardsToPlayer(p, 7);
    }
    public void tryToStealFromDeck(Player p){
        if (deck.isEmpty()) return;

        do {
            deck.giveCardsToPlayer(p, 1);
        } while(!deck.isEmpty() || !verifyPlayableCard(p, cardsGame.getCardsInGame(), p.getHandSize()));
    }

    private void showPlayableCards(Player pl, List<DominoCard> cardsGame){
        for (int i = 0; i < pl.getHandSize(); i++)
            pl.showOneCardFromHand(i, verifyPlayableCard(pl, cardsGame, i));
    }

    private void initPlayersAndGiveCards(List<Player> players){
        numberOfPlayers = inp.pickNumberOfPlayers();
        inp.createPlayerObjects(numberOfPlayers, listOfPlayers);

        for (Player playerInGame : players){
            giveCards(playerInGame);
        }
    }

    private void printPlayerHands(List<Player> players){
        for (Player playerInGame : players){
            System.out.println(" - - - - - - - - - - - - " + playerInGame.getName() + " Hand - - - - - - - - - - - - ");
            playerInGame.showHand(true);
        }
    }

    private boolean verifyPlayableCard(Player pl, List<DominoCard> cardsGame, int indexHand){
        int firstGamePos = cardsGame.get(0).getCard()[0];
        int lastGamePos = cardsGame.get(cardsGame.size() - 1).getCard()[1];

        if (pl.getCardFromHand(indexHand, 0) == firstGamePos
                || pl.getCardFromHand(indexHand, 0) == lastGamePos
                || pl.getCardFromHand(indexHand, 1) == firstGamePos
                || pl.getCardFromHand(indexHand, 1) == lastGamePos)
            return true;

        return false;
    }

    public boolean firstMove(List<Player> listPlayers, List<DominoCard> cardsGame){
        Player ref = null;

        for (int i = 0; i < (listPlayers.size() - 1); i ++){
            ref = listPlayers.get(i);
            if (listPlayers.get(i + 1).getMaxCard().compareTo(listPlayers.get(i).getMaxCard()) > 0){
                ref = listPlayers.get(i + 1);
            }
        }

        if (ref != null) {
            ref.addCardToGame(cardsGame, ref.getMaxCard(), false);
            return true;
        }
        return false;
    }
}
