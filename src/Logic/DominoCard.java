package Logic;

public class DominoCard {

    private int[] num;

    public DominoCard(int num1, int num2){
        num = new int[1];
        num[0] = num1;
        num[1] = num2;
    }

    public int getOneNum(int num){
        return this.num[num];
    }

    public int[] getCard(){
        return num;
    }

    public void setOneNum(int pos, int numToChange){
        this.num[pos] = numToChange;
    }

    public void setCard(int numPos0, int numPos1){
        this.num[0] = numPos0;
        this.num[1] = numPos1;
    }
}
