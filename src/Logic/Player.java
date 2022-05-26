package Logic;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<DominoCard> hand;

    public Player (String name){
        setName(name);
        hand = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<DominoCard> getHand(){
        return hand;
    }
}
