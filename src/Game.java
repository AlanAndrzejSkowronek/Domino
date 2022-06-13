import InOutUser.InOutUser;
import Logic.*;
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
    private int turn = 0;

    public void playGame(){
        inp.startGameMessage();
        r = inp.pickRules();
        deck = new DeckDominoCards(r);

        initPlayersAndGiveCards(listOfPlayers);
        firstMove(listOfPlayers, cardsGame.getCardsInGame());
        createTeams(listOfPlayers);

        do  {
            if(turn > (listOfPlayers.size() - 1))
                turn = 0;

            cardsGame.printActualCards();
            deck.printActualCards();
            System.out.println();
            System.out.println("Player " + listOfPlayers.get(turn).getName() + ", your turn!");
            if (listOfPlayers.get(turn).showPlayableCards(cardsGame.getCardsInGame()) <= 0)
                tryToStealFromDeck(listOfPlayers.get(turn));
            else
                listOfPlayers.get(turn)
                        .addCardToExistingGame(cardsGame.getCardsInGame(), listOfPlayers.get(turn)
                        .getCardFromHand(inp.whatCardToPlay(listOfPlayers.get(turn))));

            turn++;
        } while(!isWinner(listOfPlayers));
    }
    public void giveCards(Player p){
        deck.giveCardsToPlayer(p, 7);
    }
    public void tryToStealFromDeck(Player p){
        if (deck.isEmpty()) return;

        do{
            deck.giveCardsToPlayer(p, 1);

            if (!p.verifyPlayableCards(cardsGame.getCardsInGame())) return;

        } while (!deck.isEmpty());
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
            ref.addFirstCard(cardsGame, ref.getMaxCard());
            return true;
        }
        return false;
    }

    private void initPlayersAndGiveCards(List<Player> players){
        numberOfPlayers = inp.pickNumberOfPlayers();
        inp.createPlayerObjects(numberOfPlayers, listOfPlayers);

        for (Player playerInGame : players){
            giveCards(playerInGame);
        }
    }

    private void printPlayerHands(List<Player> players) {
        for (Player playerInGame : players) {
            System.out.println(" - - - - - - - - - - - - " + playerInGame.getName() + " Hand - - - - - - - - - - - - ");
            playerInGame.showHand(true);
            System.out.println();
        }
    }

    private void createTeams(List<Player> players){
        if (inp.wantToPlayInTeams())
            Team.create2Teams(players);
        else
            Team.createUniqueTeams(players);
    }

    /*
        condiciones
        ==
        un jugador se quede sin mano (GANARÁ EL EQUIPO),
        que los jugadores no puedan tirar ni robar en una pasada.

        Cada ronda acabada, se añaden los puntos.

        LA PARTIDA ACABA CUANDO SE LLEGA A MAX_POINTS

        ESTO PASARÁ A NORMAS
    */

    private boolean isWinner(List<Player> players){

        for (Player pl : players){
            if (pl.isPlayerHandEmpty()){
                System.out.println("Team nº" + pl.getTeam().getTeamID() + " won because of " + pl.getName() + "!!!");
                return true;
            }

            if (pl.getTeam().getPoints() >= r.getMax_points()){
                System.out.println("Team nº" + pl.getTeam().getTeamID() + " won because they reached the maximum points needed!!!");
                return true;
            }
        }

        return false;
    }
}
