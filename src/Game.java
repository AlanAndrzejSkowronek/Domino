import InOutUser.InOutUser;
import Logic.*;
import Rules.Rules;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private DeckDominoCards deck;
    private CardsInGame cardsGame = new CardsInGame();
    private Rules r;
    private final InOutUser inp = new InOutUser();
    private final List<Player> listOfPlayers = new ArrayList<>();
    private int turn = 0, roundNum = 1;

    public void playGame(){
        inp.startGameMessage();
        r = inp.pickRules();
        deck = new DeckDominoCards(r);

        initPlayersAndGiveCards(listOfPlayers);
        firstMove(listOfPlayers);
        createTeams(listOfPlayers);

        do {
            showTeamPoints(listOfPlayers);
            System.out.println("Round " + roundNum + ", let it start!");
            playRound();
            roundNum++;
            clearGame(listOfPlayers);
            initGame(listOfPlayers);
        } while(!gotMaxPoints(listOfPlayers));
        showTeamPoints(listOfPlayers);
    }

    public void initGame(List<Player> players){
        deck = new DeckDominoCards(r);
        for (Player playerInGame : players){
            giveCards(playerInGame);
        }
        firstMove(listOfPlayers);
    }

    public void clearGame(List<Player> players){
        for (Player pl : players){
            pl.clearHand();
        }
        cardsGame.clearCardsInGame();
        deck.clearDeck();
    }

    public void playRound(){
        do  {
            if(turn > (listOfPlayers.size() - 1))
                turn = 0;

            cardsGame.printActualCards();
            deck.printActualCards();
            System.out.println();
            System.out.println("Player " + listOfPlayers.get(turn).getName() + ", your turn!");

            if (showPlayableCards(listOfPlayers.get(turn)) <= 0){
                System.out.println("No tienes cartas para jugar! Robas...");
                tryToStealFromDeck(listOfPlayers.get(turn));
            } else {
                isCardFromPlayerPlayable(listOfPlayers.get(turn).getCardFromHand(inp.whatCardToPlay(listOfPlayers.get(turn))), listOfPlayers.get(turn));
            }

            turn++;
        } while(!isFinal(listOfPlayers));

        givePointsToTeams(listOfPlayers);
    }

    public void givePointsToTeams(List<Player> players){
        for (Player pl : players){
            pl.addPointsToTeam(playerTotalPointsAtRound(pl));
        }
    }

    public void showTeamPoints(List<Player> players){
        System.out.println("This is the points of teams at this round: ");
        if (players.get((players.size() - 1)).getTeamID() > 2){
            for (Player pl : players)
                System.out.println("Team " + pl.getTeamID() + " has " + pl.getPoints() +  " out of " + r.getMax_points() + " to win!");
        } else {
            System.out.println("Team " + players.get(0).getTeamID() + " has " + players.get(0).getPoints() +  " out of " + r.getMax_points() + " to win!");
            System.out.println("Team " + players.get(1).getTeamID() + " has " + players.get(1).getPoints() +  " out of " + r.getMax_points() + " to win!");
        }
    }

    public int playerTotalPointsAtRound(Player p){
        int totalPoints = 0;

        for (int i = 0; i < p.getHandSize(); i++){
            totalPoints += p.getCardFromHand(i, 0);
            totalPoints += p.getCardFromHand(i, 1);
        }

        return totalPoints;
    }

    public void giveCards(Player p){
        deck.giveCardsToPlayer(p, 7);
    }
    public void tryToStealFromDeck(Player p){
        if (deck.isEmpty()) return;

        do{
            deck.giveCardsToPlayer(p, 1);

            if (verifyPlayableCards(p)) return;

        } while (!deck.isEmpty());
    }

    public void isCardFromPlayerPlayable(DominoCard card, Player p){

        if (verifyPosPlayable(p.getIndexOfCard(card), cardsGame.getFirstGamePos(), p))
            cardsGame.addCardToGame(0, card);
        else if (verifyPosPlayable(p.getIndexOfCard(card), cardsGame.getLastGamePos(), p))
            cardsGame.addCardToGame(card);

        if (!verifyPlayableCard(p.getIndexOfCard(card), p)){
            System.out.println("Esta carta no es jugable!");
            return;
        }

        p.removeCardFromHand(card);
    }

    public boolean verifyPlayableCards(Player p){
        for (int i = 0; i < p.getHandSize(); i++){
            if(verifyPlayableCard(i, p))
                return true;
        }
        return false;
    }

    public boolean verifyPlayableCard(int indexHand, Player p){
        int firstGamePos = cardsGame.getFirstGamePos();
        int lastGamePos = cardsGame.getLastGamePos();

        return verifyPosPlayable(indexHand, firstGamePos, p)
                || verifyPosPlayable(indexHand, lastGamePos, p);
    }

    public boolean verifyPosPlayable(int indexHand, int gamePos, Player p){
        return p.getCardFromHand(indexHand, 0) == gamePos
                || p.getCardFromHand(indexHand, 1) == gamePos;
    }

    public int showPlayableCards(Player p){
        int playableCards = 0;

        for (int i = 0; i < p.getHandSize(); i++){
            p.showOneCardFromHand(i, verifyPlayableCard(i, p));
            if (verifyPlayableCard(i, p))
                playableCards++;
        }
        return playableCards;
    }

    public boolean firstMove(List<Player> listPlayers){
        Player ref = null;

        for (int i = 0; i < (listPlayers.size() - 1); i ++){
            ref = listPlayers.get(i);
            if (listPlayers.get(i + 1).getMaxCard().compareTo(listPlayers.get(i).getMaxCard()) > 0){
                ref = listPlayers.get(i + 1);
            }
        }

        if (ref != null) {
            cardsGame.addCardToGame(ref.returnFirstCard());
            return true;
        }
        return false;
    }

    private void initPlayersAndGiveCards(List<Player> players){
        int numberOfPlayers = inp.pickNumberOfPlayers();
        inp.createPlayerObjects(numberOfPlayers, listOfPlayers);

        for (Player playerInGame : players){
            giveCards(playerInGame);
        }
    }

    private void createTeams(List<Player> players){
        if (inp.wantToPlayInTeams())
            Team.create2Teams(players);
        else
            Team.createUniqueTeams(players);
    }

    private boolean isWinner(List<Player> players){

        for (Player pl : players){
            if (pl.isPlayerHandEmpty()){
                System.out.println("Team nº" + pl.getTeamID() + " won because of " + pl.getName() + "!!!");
                return true;
            }
        }
        return false;
    }

    public boolean isFinal(List<Player> players){
        return isWinner(players) ||  isLockedGame(players);
    }

    public boolean isLockedGame(List<Player> players){
        if (!deck.isEmpty()) return false;

        for (Player pl2 : players)
                    if (verifyPlayableCards(pl2))
                        return false;

        return true;
    }

    public boolean gotMaxPoints(List<Player> players) {
        for(Player pl : players){
            if (pl.getPoints() >= r.getMax_points()) {
                System.out.println("Team nº" + pl.getTeamID() + " won because they reached the maximum points needed!!!");
                return true;
            }
        }
        return false;
    }
}
