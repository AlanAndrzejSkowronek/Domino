package Logic;

import java.util.ArrayList;
import java.util.List;

public class DominoCard {

    private int num1, num2;

    public DominoCard(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    // public DominoCard(){} // Only testing

    public int[] getCard(){
        return new int[]{num1, num2};
    }

    public void printCard(DominoCard externalCard){
        if (externalCard.getCard()[0] == externalCard.getCard()[1])
            printCardHorizontally(externalCard);
        else
            printCardVertically(externalCard);
    }

    private void printCardHorizontally(DominoCard externalCard){
        System.out.println("  |---|---|");
        System.out.println("  | " + externalCard.getCard()[0] + " | " + externalCard.getCard()[1] + " |");
        System.out.println("  |---|---|");
    }
    private void printCardVertically(DominoCard externalCard){
        System.out.println("    -----");
        System.out.println("    | " + externalCard.getCard()[0] + " |");
        System.out.println("    -----");
        System.out.println("    | " + externalCard.getCard()[1] + " |");
        System.out.println("    -----");
    }
}
