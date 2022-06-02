package Logic;

import java.util.ArrayList;
import java.util.List;

public class DominoCard implements Comparable<DominoCard> {

    private int num1, num2;

    public DominoCard(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    // public DominoCard(){} // Only testing

    public int[] getCard(){
        return new int[]{num1, num2};
    }

    public boolean printCard(){
        return getCard()[0] == getCard()[1] ? printCardHorizontally() : printCardVertically();
    }

    public void printMinimalCard(boolean canBePlayed){
        if (canBePlayed)
            System.out.print("¡{ " + getCard()[0] + ", " + getCard()[1] + " }! ");
        else
            System.out.print("{ " + getCard()[0] + ", " + getCard()[1] + " } ");
    }

    public void invertCard(DominoCard card){
        int temp = card.getCard()[0];
        card.getCard()[0] = card.getCard()[1];
        card.getCard()[1] = temp;
    }
    private boolean printCardHorizontally(){
        System.out.println("  |---|---|");
        System.out.println("  | " + getCard()[0] + " | " + getCard()[1] + " |");
        System.out.println("  |---|---|");
        return true;
    }
    private boolean printCardVertically(){
        System.out.println("    -----");
        System.out.println("    | " + getCard()[0] + " |");
        System.out.println("    -----");
        System.out.println("    | " + getCard()[1] + " |");
        System.out.println("    -----");
        return true;
    }


    @Override
    public int compareTo(DominoCard dc) {
        // 1 x>y, 0 x==y, -1 x<y

        if (getCard()[0] > dc.getCard()[0] && getCard()[1] > dc.getCard()[1])
            return 1;

        if (getCard()[0] == dc.getCard()[0] && getCard()[1] == dc.getCard()[1])
            return 0;

        return -1;
    }
}
