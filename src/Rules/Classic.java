package Rules;

import Logic.DeckDominoCards;
import Logic.DominoCard;
import Logic.Player;
import java.util.List;
import GameExecution.Game;

public class Classic extends Rules {

    private Game g;
    int max_points = 80;
    public int initCards(List<DominoCard> deck){
        int numberOfCardsCreated = 0;
        for (int i = 6; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                deck.add(new DominoCard(i, j));
                numberOfCardsCreated++;
            }
        }
        return numberOfCardsCreated;
    }

    public int startNextPlayerTurn(int turn){
        return (turn + 1);
    }


    @Override
    public int getMax_points() {
        return max_points;
    }
    @Override
    public void givePointsToTeams(List<Player> players){
        players
                .get(searchPlayerWithMaxPoints(players))
                .addPointsToTeam(calculateTotalPoints(players));
    }
    @Override
    public int playerTotalPointsAtRound(Player p){
        int totalPoints = 0;

        for (int i = 0; i < p.getHandSize(); i++){
            totalPoints += p.getCardFromHand(i, 0);
            totalPoints += p.getCardFromHand(i, 1);
        }

        return totalPoints;
    }

    @Override
    public boolean isFinalizedRound(List<Player> players, DeckDominoCards deck) {
        return isWinner(players) ||  isLockedGame(players, deck);
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

    public boolean isLockedGame(List<Player> players, DeckDominoCards deck){
        if (!deck.isEmpty()) return false;

        for (Player pl2 : players)
            if (g.verifyPlayableCards(pl2))
                return false;

        System.out.println("The game is closed! No more moves!");
        return true;
    }

    private int calculateTotalPoints(List<Player> players){
        int temp = 0;
        for (Player pl : players){
            temp += playerTotalPointsAtRound(pl);
        }
        return temp;
    }

    private int searchPlayerWithMaxPoints(List<Player> players){
        int playerWithMaxPoints = 0;

        for (int i = 0; i < players.size() - 1; i++){
            if (players.get(i + 1).getPoints() > players.get(i).getPoints()){
                playerWithMaxPoints = players.indexOf(players.get(i + 1));
            }
        }
        return playerWithMaxPoints;
    }
}
