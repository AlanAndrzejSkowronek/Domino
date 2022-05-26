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

    public void printCard(){
        if (getCard()[0] == getCard()[1])
            printCardHorizontally();
        else
            printCardVertically();
    }

    private void printCardHorizontally(){
        System.out.println("  |---|---|");
        System.out.println("  | " + getCard()[0] + " | " + getCard()[1] + " |");
        System.out.println("  |---|---|");
    }
    private void printCardVertically(){
        System.out.println("    -----");
        System.out.println("    | " + getCard()[0] + " |");
        System.out.println("    -----");
        System.out.println("    | " + getCard()[1] + " |");
        System.out.println("    -----");
    }
}
