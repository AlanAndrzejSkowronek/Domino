package InOutUser;

import Rules.*;

import java.util.List;
import java.util.Scanner;
import Logic.Player;

public class InOutUser {

    private final Scanner read = new Scanner (System.in);
    private final String[] rulesList = {"classic"};


    public Rules pickRules(){

        System.out.println("Please, pick the rules you want to use in this game from this list:");
        System.out.println("    - Classic");
        System.out.print("Pick one writing the name: ");
        do {
            if (read.next().equalsIgnoreCase("classic")){
                return new Classic();
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

    public void createPlayerObjects(int numberOfPlayers, List<Player> pl){
        if (numberOfPlayers == 1){
            System.out.print("Now, pick a name for your player: ");
            pl.add(createInstanceOfPlayer(read.next()));
        } else {
            System.out.println("Now, all of you pick the name's of your players: ");
            for (int i = 1; i <= numberOfPlayers; i++){
                System.out.print("Player " + i + " pick a name, please: ");
                pl.add(createInstanceOfPlayer(read.next()));
            }
        }
    }

    public boolean wantToPlayInTeams(){
        String elec;
        System.out.println("Want to play in teams of 2?:");
        System.out.print("YES [Y] or NO [N]: ");
        elec = read.next();

        return elec.equalsIgnoreCase("y");
    }

    public void startGameMessage(){
        System.out.println("Welcome to the famous Domino Game!");
        System.out.println("Have fun!");
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
