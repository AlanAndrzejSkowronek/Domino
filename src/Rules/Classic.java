package Rules;

import Logic.DominoCard;
import Logic.Player;

import java.util.List;

public class Classic extends Rules {

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

    public void firstMove(List<Player> listPlayers){
        //TODO: Pillar la primera ficha del jugador y el jugador para que la tire a cardsInGame en game y poder empezar la partida.
    }
}
