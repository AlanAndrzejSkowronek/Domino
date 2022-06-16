package InOutUser;

import Logic.DominoCard;
import Rules.*;

import java.util.List;
import java.util.Scanner;
import Logic.Player;

public class InOutUser {

    private final Scanner read = new Scanner (System.in);
    private final String[] rulesList = {"classic"};


    public Rules pickRules(){

        System.out.println("Please, pick the rules you want to use in this game from this list:");
        System.out.println("    - classic");
        System.out.println("    - latino");
        System.out.println("    - chileno");
        System.out.print("Pick one writing the name: ");
        do {
            switch(read.next()){
                case "classic":
                    return new Classic();
                case "latino":
                    return new Latino();
                case "chileno":
                    return new Chileno();
                default:
                    System.out.print("That type of rules doesn't exist! Try typing one from the list above: ");
            }
        } while (!verifyInputOfRules());
        return null;
    }

    public int pickNumberOfPlayers(){
        System.out.print("Please, pick the number of players: ");
        int numberOfPlayers, i = 0;
        do {
            if (i > 0){
                System.out.print("That's not a validated number of players! Pick between 1 and 4: ");
            }
            numberOfPlayers = read.nextInt();

            i++;
        } while(numberOfPlayers <= 0 || numberOfPlayers >= 5);
        return numberOfPlayers;
    }

    public int whatCardToPlay(Player pl){
        int cardToPlay = 0;
        System.out.println();
        do {
            System.out.println("What card do you want to play?: ");

            cardToPlay = (read.nextInt() - 1);

            if (!verifyRangeOfHand(pl, cardToPlay)){
                System.out.println("Introduce the number of the card in your hand!");
            }
        } while(!verifyRangeOfHand(pl, cardToPlay));
        return cardToPlay;
    }

    public void createPlayerObjects(int numberOfPlayers, List<Player> pl, Rules r){
        if (r instanceof Classic || r instanceof Chileno)
            pickPlayers(numberOfPlayers, pl);

        if (r instanceof Latino)
            for (int i = 0; i < numberOfPlayers; i++)
                pickName(pl);
    }

    private void pickPlayers(int numberOfPlayers, List<Player> pl){
        if (numberOfPlayers == 1){
            pickName(pl);
        } else {
            System.out.println("Now, all of you pick the name's of your players: ");
            for (int i = 1; i <= numberOfPlayers; i++){
                System.out.print("Player " + i + " pick a name, please: ");
                pl.add(createInstanceOfPlayer(read.next()));
            }
        }
    }

    private void pickName(List<Player> pl){
        System.out.print("Now, pick a name for your player: ");
        pl.add(createInstanceOfPlayer(read.next()));
    }

    public boolean wantToPlayInTeams(){
        String elec;
        System.out.println("Want to play in teams of 2?:");
        System.out.print("YES [Y] or NO [N]: ");
        elec = read.next();

        return elec.equalsIgnoreCase("y");
    }

    public void startGameMessage(){
        System.out.println("Welcome to the famous Domino GameExecution.Game!");
        System.out.println("Have fun!");
    }

    private boolean verifyRangeOfHand(Player pl, int cardToPlay){
        return cardToPlay >= 0 && cardToPlay < pl.getHandSize();
    }

    private boolean verifyInputOfRules(){
        for (String rule : rulesList)
            if (read.next().equalsIgnoreCase(rule))
                return true;

        return false;
    }

    private Player createInstanceOfPlayer(String name){
        return new Player(name);
    }
}
