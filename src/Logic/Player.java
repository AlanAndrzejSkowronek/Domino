package Logic;

import java.util.List;

public class Player {

    private String name;
    private List<DominoCard> hand;

    public Player (String name){
        setName(name);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
