package Rules;

import Logic.CardsInGame;
import Logic.DominoCard;
import Logic.Player;

import java.util.List;

public class Classic extends Rules {

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

    @Override
    public int getMax_points() {
        return max_points;
    }

    public void givePointsToTeams(List<Player> players){
        players
                .get(searchPlayerWithMaxPoints(players))
                .addPointsToTeam(calculateTotalPoints(players));
    }

    public int playerTotalPointsAtRound(Player p){
        int totalPoints = 0;

        for (int i = 0; i < p.getHandSize(); i++){
            totalPoints += p.getCardFromHand(i, 0);
            totalPoints += p.getCardFromHand(i, 1);
        }

        return totalPoints;
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
