package Rules;

import Logic.DominoCard;
import Logic.Player;
import java.util.List;

public class Latino extends Rules {

    private final int max_points = 200;

    @Override
    public int initCards(List<DominoCard> deck) {
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

    @Override
    public void givePointsToTeams(List<Player> players){
        players
                .get(searchPlayerWithMaxPoints(players))
                .addPointsToTeam(
                        playerTotalPointsAtRound(
                                players.get(getTeammate(players, getTeamIdxPlayerMaxPoints(players)))));

        players
                .get(searchPlayerWithMaxPoints(players))
                .addPointsToTeam(
                        playerTotalPointsAtRound(
                                players.get(searchPlayerWithMaxPoints(players))));
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

    private int getTeammate(List<Player> players, int teamIndex){
        for (int i = 0; i < players.size(); i++){
            if (teamIndex == players.get(i).getTeamID()){
                return players.indexOf(players.get(i));
            }
        }
        return -1;
    }

    private int getTeamIdxPlayerMaxPoints(List<Player> players){
        return players.get(searchPlayerWithMaxPoints(players)).getTeamID();
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
