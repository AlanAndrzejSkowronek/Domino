package GameExecution;

import InOutUser.InOutUser;
import Logic.*;
import Rules.*;
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
            if (r instanceof Classic) turn = ((Classic) r).startNextPlayerTurn(turn);
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

    public void giveCards(Player p){
        deck.giveCardsToPlayer(p, 7);
    }

    public void playRound(){
        do  {
            if(turn > (listOfPlayers.size() - 1))
                turn = 0;

            cardsGame.printActualCards();
            // -> deck.printActualCards(); <- Only testing!
            displayPlayerTurn(turn);

            if (countPlayableCards(listOfPlayers.get(turn)) <= 0)
                displayStealingCards(turn);
            else
                isCardFromPlayerPlayable(listOfPlayers.get(turn));

            turn++;
        } while(!isFinalizedRound(listOfPlayers));

        r.givePointsToTeams(listOfPlayers);
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

    public void tryToStealFromDeck(Player p){
        if (deck.isEmpty()) return;

        do{
            deck.giveCardsToPlayer(p, 1);

            if (verifyPlayableCard((p.getHandSize() - 1), p)){
                isCardFromPlayerPlayable(p);
                return;
            }

        } while (!deck.isEmpty());
    }

    public void isCardFromPlayerPlayable(Player p) {

        DominoCard card;
        do {
            showPlayableCards(listOfPlayers.get(turn));
            card = p.getCardFromHand(inp.whatCardToPlay(listOfPlayers.get(turn)));
            if (verifyPosPlayable(p.getIndexOfCard(card), cardsGame.getFirstGamePos(), p)){
                if (card.getCard()[1] != cardsGame.getFirstGamePos()){
                    card.invertCard();
                }
                cardsGame.addCardToGame(0, card);
            }
            else if (verifyPosPlayable(p.getIndexOfCard(card), cardsGame.getLastGamePos(), p)){
                if (card.getCard()[0] != cardsGame.getLastGamePos()){
                    card.invertCard();
                }
                cardsGame.addCardToGame(card);
            }
        } while (!verifyPlayableCard(p.getIndexOfCard(card), p));

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

    public int countPlayableCards(Player p){
        int playableCards = 0;

        for (int i = 0; i < p.getHandSize(); i++){
            if (verifyPlayableCard(i, p))
                playableCards++;
        }
        return playableCards;
    }

    public void showPlayableCards(Player p){
        for (int i = 0; i < p.getHandSize(); i++){
            p.showOneCardFromHand(i, verifyPlayableCard(i, p));
        }
    }

    public boolean firstMove(List<Player> listPlayers){
        Player ref = null;
        shuffleHandsOfPlayers(listPlayers);
        for (int i = 0; i < (listPlayers.size() - 1); i ++){
            ref = listPlayers.get(i);
            if (listPlayers.get(i + 1).getMaxCard().compareTo(listPlayers.get(i).getMaxCard()) > 0){
                ref = listPlayers.get(i + 1);
            }
        }
        turn = listPlayers.indexOf(ref);
        if (ref != null) {
            cardsGame.addCardToGame(ref.returnFirstCard());
            return true;
        }
        return false;
    }

    public void shuffleHandsOfPlayers(List<Player> players){
        for (Player p : players){
            p.shuffleHand();
        }
    }

    public boolean isWinner(List<Player> players){

        for (Player pl : players){
            if (pl.isPlayerHandEmpty()){
                if (r instanceof Latino)
                    pl.addPointsToTeam(25);

                System.out.println("Team nº" + pl.getTeamID() + " won because of " + pl.getName() + "!!!");
                return true;
            }
        }
        return false;
    }

    public boolean isLockedGame(List<Player> players){
        if (!deck.isEmpty()) return false;

        for (Player pl2 : players)
            if (verifyPlayableCards(pl2))
                return false;

        System.out.println("The game is closed! No more moves!");
        return true;
    }

    private void displayPlayerTurn(int turn){
        System.out.println();
        System.out.println("Player " + listOfPlayers.get(turn).getName() + ", your turn!");
        System.out.println();
    }

    private void displayStealingCards(int turn){
        System.out.println();
        System.out.println("You don't have cards to use! Stealing...");
        tryToStealFromDeck(listOfPlayers.get(turn));
    }

    private void initPlayersAndGiveCards(List<Player> players){
        int numberOfPlayers = 0;
        if (r instanceof Classic || r instanceof Chileno)
            numberOfPlayers = inp.pickNumberOfPlayers();

        if (r instanceof Latino)
            numberOfPlayers = 4;

        inp.createPlayerObjects(numberOfPlayers, listOfPlayers, r);

        for (Player playerInGame : players){
            giveCards(playerInGame);
        }
    }

    private void createTeams(List<Player> players){
        if (r instanceof Latino)
            Team.create2Teams(players);

        if (r instanceof Classic || r instanceof Chileno)
            if (inp.wantToPlayInTeams())
                Team.create2Teams(players);
            else
                Team.createUniqueTeams(players);
    }

    public boolean isFinalizedRound(List<Player> players){
        return isWinner(players) || isLockedGame(players);
    }

    public boolean gotMaxPoints(List<Player> players) {
        for(Player pl : players){
            if (pl.getPoints() >= r.getMax_points()) {
                if (r instanceof Latino || r instanceof Chileno){
                    System.out.println("Team nº" + pl.getTeamID() + " lost because they reached the maximum points!!!");
                    System.out.println("Team nº" + getTeamWithLowerPoints(players) + " won the game!!!");
                } else {
                    System.out.println("Team nº" + pl.getTeamID() + " won because they reached the maximum points needed!!!");
                }
                return true;
            }
        }
        return false;
    }

    private int getTeamWithLowerPoints(List<Player> players){
        int teamWithLowerPoints = 0;

        for (int i = 0; i < players.size() - 1; i++)
            if (players.get(i + 1).getPoints() > players.get(i).getPoints())
                teamWithLowerPoints = players.get(players.indexOf(players.get(i + 1))).getTeamID();

        return teamWithLowerPoints;
    }
}
